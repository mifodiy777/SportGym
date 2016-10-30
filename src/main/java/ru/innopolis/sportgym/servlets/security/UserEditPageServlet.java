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
 * Created by Кирилл on 29.10.2016.
 */
public class UserEditPageServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text / html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Integer id = Integer.parseInt(req.getParameter("idUser"));
        req.setAttribute("user", service.getUser(id));
       /* PrintWriter out = resp.getWriter();
        out.write();*/
        req.getRequestDispatcher("editUser.jsp").forward(req, resp);

    }
}
