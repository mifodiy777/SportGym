package ru.innopolis.sportgym.servlets.security;

import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.service.UserService;
import ru.innopolis.sportgym.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Кирилл on 30.10.2016.
 */
public class UserEditServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text / html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("id")));
        user.setSurname(req.getParameter("surname"));
        user.setName(req.getParameter("name"));
        user.setPatronymic(req.getParameter("patronymic"));
        user.setPhone(req.getParameter("phone"));
        PrintWriter out = resp.getWriter();
        if (service.editUser(user)) {
           out.write("Пользователь успешно изменен!");
        } else {
            resp.setStatus(409);
            out.write("Ошибка!Попробуйте снова!");
        }


    }
}
