package ru.innopolis.sportgym.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.sportgym.editor.CalendarCustomEditor;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

import static ru.innopolis.sportgym.config.Constance.DATE_FORMAT;

/**
 * Created by Кирилл on 19.11.2016.
 */
@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Calendar.class, new CalendarCustomEditor(DATE_FORMAT));
    }

    /**
     * Страница регистрации
     *
     * @return Страница регистрации
     */
    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    /**
     * Сохранение нового пользователя
     *
     * @return переход на страницу авторизации
     */
    @RequestMapping(value = {"/saveProfile"}, method = RequestMethod.POST)
    public String saveProfile(User user, ModelMap map) {
        try {
            userService.saveProfile(user);
            return "redirect:login";
        } catch (DataSQLException e) {
            map.addAttribute("errorMsg", "Ошибка: Такой пользователь уже существует! Попробуйте снова");
            logger.error("Ошибка: Такой пользователь уже существует! Попробуйте снова", e);
            return "registration";
        }
    }

    @RequestMapping(value = {"/editUserPage/{id}"}, method = RequestMethod.GET)
    public String editUserPage(@PathVariable("id") Integer id, ModelMap map, HttpServletResponse response) {
        try {
            map.addAttribute("user", userService.getUser(id));
            return "editUser";
        } catch (DataSQLException e) {
            map.addAttribute("message", "Ошибка получения данных для формы");
            logger.error("Ошибка получения данных для формы", e);
            response.setStatus(409);
            return "error";
        }
    }

    @RequestMapping(value = {"/editProfile"}, method = RequestMethod.POST)
    public String editProfile(User user, ModelMap map, HttpServletResponse response, HttpSession session) {
        try {
            user.setPassword(userService.getUser(user.getId()).getPassword());
            userService.saveProfile(user);
            session.setAttribute("userCurrent", user);
            map.addAttribute("message", "Пользователь успешно сохранен!");
            return "success";
        } catch (DataSQLException e) {
            response.setStatus(409);
            map.addAttribute("message", "Ошибка: Ошибка сохранения пользователя");
            logger.error("Ошибка: Ошибка сохранения пользователя", e);
            return "error";
        }
    }

}
