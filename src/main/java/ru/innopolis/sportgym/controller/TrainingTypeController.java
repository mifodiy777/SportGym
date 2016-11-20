package ru.innopolis.sportgym.controller;

import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import ru.innopolis.sportgym.service.TrainingTypeService;
import ru.innopolis.sportgym.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
     * Страница регистрации
     *
     * @return Страница регистрации
     */
    @RequestMapping(value = {"/traningTypePage"}, method = RequestMethod.GET)
    public String getTrainigTypePage() {
        return "trainingtypes";
    }

    @RequestMapping(value = "allTrainigTypes", method = RequestMethod.POST)
    public ResponseEntity<String> getAllTrainigType(HttpSession session) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        User user = (User) session.getAttribute("userCurrent");
        try {
            return Utils.convertListToJson(gsonBuilder, typeService.findByUser(user));
        } catch (DataSQLException e) {
            return ResponseEntity.status(409).body("Error");
        }
    }

    @RequestMapping(value = {"trainigTypeForm"}, method = RequestMethod.GET)
    public String getTrainigTypeForm(HttpSession session, ModelMap map) {
        TrainingType trainingType = new TrainingType();
        trainingType.setUser((User) session.getAttribute("userCurrent"));
        map.addAttribute("trainigType", trainingType);
        return "trainingtype";


    }

    @RequestMapping(value = {"/saveTrainigType"}, method = RequestMethod.POST)
    public String saveTrainigType(TrainingType trainingType, ModelMap map, HttpServletResponse response) {
        try {
            typeService.saveTrainigType(trainingType);
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
     * @return сообщение
     */
    @RequestMapping(value = "deleteTrainingType/{id}", method = RequestMethod.POST)
    public String deleteTrainingType(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        try {
            typeService.deleteTrainigType(id);
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
