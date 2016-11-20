package ru.innopolis.sportgym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by Кирилл on 02.11.2016.
 */
@Controller
public class SecurityController {

    private final UserService userService;

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Стартовая страница
     *
     * @return стартовая страница
     */
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String start(HttpSession session) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            session.setAttribute("userCurrent", userService.findByLogin(user.getUsername()));
        } catch (DataSQLException e) {
            e.printStackTrace();
        }
        return "index";
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
