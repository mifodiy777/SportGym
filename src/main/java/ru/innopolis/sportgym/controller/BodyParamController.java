package ru.innopolis.sportgym.controller;

import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.sportgym.Utils;
import ru.innopolis.sportgym.editor.CalendarCustomEditor;
import ru.innopolis.sportgym.editor.UserEditor;
import ru.innopolis.sportgym.entity.BodyParam;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.gson.CalendarAdapter;
import ru.innopolis.sportgym.gson.ChartAdapter;
import ru.innopolis.sportgym.service.BodyParamService;
import ru.innopolis.sportgym.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static ru.innopolis.sportgym.config.Constance.DATE_FORMAT;

/**
 * Created by Кирилл on 19.11.2016.
 */
@Controller
public class BodyParamController {

    private static Logger logger = LoggerFactory.getLogger(BodyParamController.class);

    private BodyParamService paramService;

    private UserService userService;


    @Autowired
    public BodyParamController(BodyParamService paramService, UserService userService) {
        this.paramService = paramService;
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Calendar.class, new CalendarCustomEditor(DATE_FORMAT));
        binder.registerCustomEditor(User.class, new UserEditor(userService));
    }

    /**
     * Страница регистрации
     *
     * @return Страница регистрации
     */
    @RequestMapping(value = {"/bodyParamsPage"}, method = RequestMethod.GET)
    public String getParamPage() {
        return "bodyparams";
    }

    @RequestMapping(value = "allBodyParams", method = RequestMethod.POST)
    public ResponseEntity<String> getAllBudyParam(HttpSession session) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        User user = (User) session.getAttribute("userCurrent");
        gsonBuilder.registerTypeAdapter(GregorianCalendar.class, new CalendarAdapter(DATE_FORMAT));
        try {
            return Utils.convertListToJson(gsonBuilder, paramService.findByUser(user));
        } catch (DataSQLException e) {
            return ResponseEntity.status(409).body("Error");
        }
    }

    @RequestMapping(value = "createGraf", method = RequestMethod.POST)
    @ResponseBody
    public String createGraf(@RequestParam("param") String param,HttpSession session, HttpServletResponse response ) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        User user = (User) session.getAttribute("userCurrent");
        gsonBuilder.registerTypeAdapter(BodyParam.class, new ChartAdapter(param));
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            return Utils.convertDataChart(gsonBuilder, paramService.findByUser(user));
        } catch (DataSQLException e) {
            return null;
        }
    }


    @RequestMapping(value = {"bodyParamForm"}, method = RequestMethod.GET)
    public String getBodyParamForm(HttpSession session, ModelMap map) {
        BodyParam bodyParam = new BodyParam();
        bodyParam.setUser((User) session.getAttribute("userCurrent"));
        map.addAttribute("bodyParam", bodyParam);
        return "bodyparam";
    }

    @RequestMapping(value = "bodyParamForm/{id}", method = RequestMethod.GET)
    public String getBodyParamForm(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        try {
            map.addAttribute("bodyParam", paramService.getBodyParam(id));
            return "bodyparam";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка получения физиологических параметров");
            logger.error("Ошибка получения физиологических параметров", e);
            return "error";
        }

    }

    @RequestMapping(value = {"/saveBodyParam"}, method = RequestMethod.POST)
    public String saveBodyParam(BodyParam param, ModelMap map, HttpServletResponse response) {
        try {
            param.setMeasurementDate(Calendar.getInstance());
            paramService.saveBodyParam(param);
            map.addAttribute("message", "Параметры успешно добавленны!");
            return "success";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка сохранения параметров");
            logger.error("Ошибка сохранения параметров", e);
            return "error";
        }
    }

    /**
     * Метод удаляет физиологические параметры
     *
     * @param id       id записи физиологических параметров
     * @param map      ModelMap
     * @param response response
     * @return сообщение
     */
    @RequestMapping(value = "deleteBodyParam/{id}", method = RequestMethod.POST)
    public String deleteBodyParam(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        try {
            paramService.deleteBodyParam(id);
            map.put("message", "Запись физиологических параметров удалена!");
            return "success";
        } catch (DataSQLException e) {
            map.put("message", "Невозможно удалить");
            logger.error("Ошибка удаления физ.параметров", e);
            response.setStatus(409);
            return "error";
        }

    }

}
