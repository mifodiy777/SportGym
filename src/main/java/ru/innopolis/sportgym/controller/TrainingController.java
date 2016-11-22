package ru.innopolis.sportgym.controller;

import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.sportgym.Utils;
import ru.innopolis.sportgym.editor.CalendarCustomEditor;
import ru.innopolis.sportgym.editor.TrainingTypeEditor;
import ru.innopolis.sportgym.editor.UserEditor;
import ru.innopolis.sportgym.entity.Training;
import ru.innopolis.sportgym.entity.TrainingType;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.gson.CalendarAdapter;
import ru.innopolis.sportgym.gson.UserAdapter;
import ru.innopolis.sportgym.service.TrainingService;
import ru.innopolis.sportgym.service.TrainingTypeService;
import ru.innopolis.sportgym.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static ru.innopolis.sportgym.config.Constance.DATETIME_FORMAT;

/**
 * Created by Кирилл on 20.11.2016.
 */
@Controller
public class TrainingController {

    private static Logger logger = LoggerFactory.getLogger(TrainingController.class);

    private final TrainingService trainingService;

    private final TrainingTypeService typeService;

    private final UserService userService;

    @Autowired
    public TrainingController(TrainingTypeService typeService, TrainingService trainingService, UserService userService) {
        this.typeService = typeService;
        this.trainingService = trainingService;
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new UserEditor(userService));
        binder.registerCustomEditor(TrainingType.class, new TrainingTypeEditor(typeService));
        binder.registerCustomEditor(Calendar.class, new CalendarCustomEditor(DATETIME_FORMAT));
    }

    /**
     * Страница тренировок
     *
     * @return jsp
     */
    @RequestMapping(value = "trainingPage", method = RequestMethod.GET)
    public String getTrainingPage(@RequestParam(required = false, value = "id") Integer id, ModelMap map) {
        try {
            List<TrainingType> trainingTypes = typeService.findByUser(userService.getCurrentUser());
            if (id != null) {
                map.addAttribute("currentType", typeService.getTrainingType(id));
            } else if (trainingTypes.size() > 0) {
                map.addAttribute("currentType", trainingTypes.get(0));
            }
            map.addAttribute("trainingType", trainingTypes);
            return "trainings";
        } catch (DataSQLException e) {
            return "404";
        }
    }

    /**
     * Список тренировок определенного User'а и определенного типа
     * @param id типа тренировок
     * @return JSON
     */
    @RequestMapping(value = "allTrainings/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> getAllTraining(@PathVariable("id") Integer id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(GregorianCalendar.class, new CalendarAdapter(DATETIME_FORMAT));
        gsonBuilder.registerTypeAdapter(User.class, new UserAdapter());
        try {
            return Utils.convertListToJson(gsonBuilder, trainingService.findByUserAndType(userService.getCurrentUser(), typeService.getTrainingType(id)));
        } catch (DataSQLException e) {
            return ResponseEntity.status(409).body("Error");
        }
    }

    /**
     * RESTful сервис получения списка всех тренировок(не выполненных)
     * @param response
     * @return JSON
     */
    @RequestMapping(value = "getTrainings", method = RequestMethod.GET)
    @ResponseBody
    public String createGraf(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(GregorianCalendar.class, new CalendarAdapter(DATETIME_FORMAT));
        gsonBuilder.registerTypeAdapter(User.class, new UserAdapter());
        try {
            return Utils.convertJSON(gsonBuilder, trainingService.findByUserAndComplete(userService.getCurrentUser(), false));
        } catch (DataSQLException e) {
            return null;
        }
    }

    /**
     * Форма добавления тренировок
     * @param typeId тип
     * @param map
     * @param response
     * @return jsp
     */
    @RequestMapping(value = "trainingForm", method = RequestMethod.GET)
    public String getTrainingForm(@RequestParam("type") Integer typeId, ModelMap map, HttpServletResponse response) {
        try {
            Training training = new Training();
            training.setType(typeService.getTrainingType(typeId));
            training.setUser(userService.getCurrentUser());
            training.setComplete(false);
            map.addAttribute("training", training);
            return "training";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка получения данных для формы тренировки");
            logger.error("Ошибка получения данных для формы тренировки", e);
            return "error";
        }
    }

    /**
     * Форма редактирования тренировки (в зависимости от статуса подгружается своя форма редактирования)
     * @param id тренировки
     * @param map
     * @param response
     * @return jsp
     */
    @RequestMapping(value = "editTraining/{id}", method = RequestMethod.GET)
    public String editTraining(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        try {
            Training training = trainingService.getTraining(id);
            map.addAttribute("training", training);
            return (training.getComplete()) ? "completeTraining" : "training";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка получения данных для формы тренировки");
            logger.error("Ошибка получения данных для формы тренировки", e);
            return "error";
        }
    }

    /**
     * Форма изменения статуса тренировки(требуется заполнение результатов)
     * @param id тренировки
     * @param map
     * @param response
     * @return jsp
     */
    @RequestMapping(value = "trainingComplete/{id}", method = RequestMethod.GET)
    public String trainingComplete(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        try {
            map.addAttribute("training", trainingService.getTraining(id));
            return "completeTraining";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка получения данных для формы тренировки");
            logger.error("Ошибка получения данных для формы тренировки", e);
            return "error";
        }
    }

    /**
     * Сохранение тренировки
     * @param training
     * @param alarm режим напоминания
     * @param map
     * @param response
     * @return msg
     */
    @RequestMapping(value = "/saveTraining", method = RequestMethod.POST)
    public String saveTraining(Training training, @RequestParam(value = "alarm", required = false) String alarm, ModelMap map, HttpServletResponse response) {
        try {
            if (alarm != null) {
                training.setNotificate(trainingService.getNotificate(training.getTargetDate(), alarm));
            }
            trainingService.saveTraining(training);
            map.addAttribute("message", "Тренировка успешно добавлена!");
            return "success";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка сохранения тренировки");
            logger.error("Ошибка сохранения типов тренировки", e);
            return "error";
        }
    }

    /**
     * Метод удаляет типы тренировок
     *
     * @param id       Тип тренировок
     * @param map      ModelMap
     * @param response response
     * @return msg
     */
    @RequestMapping(value = "deleteTraining/{id}", method = RequestMethod.POST)
    public String deleteTraining(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        try {
            trainingService.deleteTraining(id);
            map.put("message", "Тренировка удалена!");
            return "success";
        } catch (DataSQLException e) {
            map.put("message", "Невозможно удалить");
            logger.error("Ошибка удаления тренировки", e);
            response.setStatus(409);
            return "error";
        }

    }
}
