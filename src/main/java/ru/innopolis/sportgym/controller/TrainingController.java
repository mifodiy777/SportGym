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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

import static ru.innopolis.sportgym.config.Constance.DATETIME_FORMAT;

/**
 * Created by Кирилл on 20.11.2016.
 */
@Controller
public class TrainingController {

    private static Logger logger = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private TrainingTypeService typeService;

    @Autowired
    private UserService userService;

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
    @RequestMapping(value = {"/traningPage"}, method = RequestMethod.GET)
    public String getTrainigPage(HttpSession session, ModelMap map) {
        User user = (User) session.getAttribute("userCurrent");
        try {
            map.addAttribute("trainingType", typeService.findByUser(user));
            return "trainings";
        } catch (DataSQLException e) {
            return "404";
        }
    }

    @RequestMapping(value = "allTrainigs", method = RequestMethod.POST)
    public ResponseEntity<String> getAllTrainig(HttpSession session) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        User user = (User) session.getAttribute("userCurrent");
        gsonBuilder.registerTypeAdapter(GregorianCalendar.class, new CalendarAdapter(DATETIME_FORMAT));
        try {
            return Utils.convertListToJson(gsonBuilder, trainingService.findByUser(user));
        } catch (DataSQLException e) {
            return ResponseEntity.status(409).body("Error");
        }
    }

    @RequestMapping(value = {"trainigForm"}, method = RequestMethod.GET)
    public String getTrainigForm(HttpSession session, ModelMap map, HttpServletResponse response) {
        Training training = new Training();
        training.setUser((User) session.getAttribute("userCurrent"));
        map.addAttribute("trainig", training);
        try {
            map.addAttribute("trainigType", typeService.findByUser(training.getUser()));
            return "training";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка получения данных для формы тренировки");
            logger.error("Ошибка получения данных для формы тренировки", e);
            return "error";
        }


    }

    @RequestMapping(value = {"/saveTrainig"}, method = RequestMethod.POST)
    public String saveTrainig(Training training, ModelMap map, HttpServletResponse response) {
        try {
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
}
