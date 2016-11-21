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
import ru.innopolis.sportgym.service.TrainingService;
import ru.innopolis.sportgym.service.TrainingTypeService;
import ru.innopolis.sportgym.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
     * Страница регистрации
     *
     * @return Страница регистрации
     */
    @RequestMapping(value = "traningPage", method = RequestMethod.GET)
    public String getTrainigPage(@RequestParam(required = false, value = "id") Integer id, HttpSession session, ModelMap map) {
        User user = (User) session.getAttribute("userCurrent");
        try {
            List<TrainingType> trainingTypes = typeService.findByUser(user);
            if (id != null) {
                map.addAttribute("currentType", typeService.getTrainigType(id));
            } else if (trainingTypes.size() > 0) {
                map.addAttribute("currentType", trainingTypes.get(0));
            }
            map.addAttribute("trainingType", trainingTypes);
            return "trainings";
        } catch (DataSQLException e) {
            return "404";
        }
    }

    @RequestMapping(value = "allTrainigs/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> getAllTrainig(@PathVariable("id") Integer id, HttpSession session) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        User user = (User) session.getAttribute("userCurrent");
        gsonBuilder.registerTypeAdapter(GregorianCalendar.class, new CalendarAdapter(DATETIME_FORMAT));
        try {
            TrainingType type = typeService.getTrainigType(id);
            return Utils.convertListToJson(gsonBuilder, trainingService.findByUserAndType(user, type));
        } catch (DataSQLException e) {
            return ResponseEntity.status(409).body("Error");
        }
    }

    @RequestMapping(value = "trainigForm", method = RequestMethod.GET)
    public String getTrainigForm(@RequestParam("type") Integer typeId, HttpSession session, ModelMap map, HttpServletResponse response) {
        try {
            Training training = new Training();
            training.setType(typeService.getTrainigType(typeId));
            training.setUser((User) session.getAttribute("userCurrent"));
            map.addAttribute("trainig", training);
            return "training";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка получения данных для формы тренировки");
            logger.error("Ошибка получения данных для формы тренировки", e);
            return "error";
        }
    }

    @RequestMapping(value = "editTraining/{id}", method = RequestMethod.GET)
    public String editTraining(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        try {
            Training training = trainingService.getTraining(id);
            map.addAttribute("trainig", training);
            return (training.getComplete()) ? "completeTraining" : "training";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка получения данных для формы тренировки");
            logger.error("Ошибка получения данных для формы тренировки", e);
            return "error";
        }
    }

    @RequestMapping(value = "trainigComplete/{id}", method = RequestMethod.GET)
    public String trainigComplete(@PathVariable("id") Integer id, HttpSession session, ModelMap map, HttpServletResponse response) {
        try {
            map.addAttribute("trainig", trainingService.getTraining(id));
            return "completeTraining";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка получения данных для формы тренировки");
            logger.error("Ошибка получения данных для формы тренировки", e);
            return "error";
        }
    }

    @RequestMapping(value = "/saveTrainig", method = RequestMethod.POST)
    public String saveTrainig(Training training, @RequestParam(value = "alarm", required = false) String alarm, ModelMap map, HttpServletResponse response) {
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
     * @return сообщение
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
