package ru.innopolis.sportgym.controller;

import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.sportgym.Utils;
import ru.innopolis.sportgym.editor.UserEditor;
import ru.innopolis.sportgym.entity.TrainingType;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.gson.UserAdapter;
import ru.innopolis.sportgym.service.TrainingTypeService;
import ru.innopolis.sportgym.service.UserService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Кирилл on 20.11.2016.
 */
@Controller
public class TrainingTypeController {

    private static Logger logger = LoggerFactory.getLogger(TrainingTypeController.class);

    private final TrainingTypeService typeService;

    private final UserService userService;


    @Autowired
    public TrainingTypeController(TrainingTypeService typeService, UserService userService) {
        this.typeService = typeService;
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new UserEditor(userService));
    }

    /**
     * Страница типов тренировок
     *
     * @return jsp
     */
    @RequestMapping(value = "/trainingTypePage", method = RequestMethod.GET)
    public String getTrainingTypePage() {
        return "trainingtypes";
    }

    /**
     * Список всех типов тренировок определенного User'а
     *
     * @return JSON
     */
    @RequestMapping(value = "allTrainingTypes", method = RequestMethod.POST)
    public ResponseEntity<String> getAllTrainingType() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserAdapter());
        try {
            return Utils.convertListToJson(gsonBuilder, typeService.findByUser(userService.getCurrentUser()));
        } catch (DataSQLException e) {
            return ResponseEntity.status(409).body("Error");
        }
    }

    /**
     * Форма добавления типов тренировок
     *
     * @param map
     * @return jsp
     */
    @RequestMapping(value = "trainingTypeForm", method = RequestMethod.GET)
    public String getTrainingTypeForm(ModelMap map) {
        TrainingType trainingType = new TrainingType();
        trainingType.setUser(userService.getCurrentUser());
        map.addAttribute("trainingType", trainingType);
        return "trainingtype";


    }

    /**
     * Сохранение типа тренировок
     * @param trainingType тип тренировок
     * @param map
     * @param response
     * @return msg
     */
    @RequestMapping(value = "/saveTrainingType", method = RequestMethod.POST)
    public String saveTrainingType(TrainingType trainingType, ModelMap map, HttpServletResponse response) {
        try {
            typeService.saveTrainingType(trainingType);
            map.addAttribute("message", "Тип тренировок успешно добавлен!");
            return "success";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка сохранения типов тренировок");
            logger.error("Ошибка сохранения типов тренировок", e);
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
    @RequestMapping(value = "deleteTrainingType/{id}", method = RequestMethod.POST)
    public String deleteTrainingType(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        try {
            typeService.deleteTrainingType(id);
            map.put("message", "Тип тренировок удален!");
            return "success";
        } catch (DataSQLException e) {
            map.put("message", "Невозможно удалить");
            logger.error("Ошибка удаления типов тренировок", e);
            response.setStatus(409);
            return "error";
        }

    }

}
