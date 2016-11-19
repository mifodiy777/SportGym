package ru.innopolis.sportgym.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.sportgym.editor.DateCustomEditor;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.service.UserService;

import java.util.Date;

/**
 * Created by Кирилл on 02.11.2016.
 */
@Controller
public class SecurityController {

    private static Logger logger = LoggerFactory.getLogger(SecurityController.class);

    private UserService userService;

    @Autowired
    public SecurityController(UserService service) {
        this.userService = service;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateCustomEditor());
    }

    /**
     * Стартовая страница
     *
     * @return стартовая страница
     */
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String start() {
        return "index";
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
            map.addAttribute("Ошибка: Такой пользователь уже существует! Попробуйте снова");
            logger.error("Пользователь не  сохранен", e);
            return "registration";
        }
    }


    /**
     * Форма авторизации
     *
     * @param error  msg об ошибке
     * @param logout msg о выходе из сессии
     * @param model
     * @return форма
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "Неправильный логин или пароль!");
        }
        if (logout != null) {
            model.addAttribute("msg", "Вы вышли");
        }
        return "login";
    }
}
