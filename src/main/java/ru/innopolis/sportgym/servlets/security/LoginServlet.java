package ru.innopolis.sportgym.servlets.security;

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
public class LoginServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text / html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        Integer idUser = service.checkPermission(login, password);
        if (idUser != null) {
            req.setAttribute("id",idUser);
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMsg", "Неправильные данные для входа. Пожалуйста, попробуйте снова.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }


    }
}
