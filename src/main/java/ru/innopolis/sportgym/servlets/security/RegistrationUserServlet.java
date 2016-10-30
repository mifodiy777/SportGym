package ru.innopolis.sportgym.servlets.security;

import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.service.UserService;
import ru.innopolis.sportgym.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Кирилл on 28.10.2016.
 */
public class RegistrationUserServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text / html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        User user = new User();
        user.setSurname(req.getParameter("surname"));
        user.setName(req.getParameter("name"));
        user.setPatronymic(req.getParameter("patronymic"));
        user.setEmail(req.getParameter("email"));
        user.setActive(true);
        user.setPhone(req.getParameter("phone"));
        user.setPassword(req.getParameter("password"));
        if (service.addUser(user)) {
            req.getRequestDispatcher("/").forward(req, resp);
        } else {
            req.setAttribute("errorMsg","Ошибка: Такой пользователь уже существует! Попробуйте снова");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);

        }


    }
}
