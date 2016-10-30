package ru.innopolis.sportgym.servlets.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Кирилл on 30.10.2016.
 */
public class LoginPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text / html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.setAttribute("id", session.getAttribute("user"));
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }
    }
}
