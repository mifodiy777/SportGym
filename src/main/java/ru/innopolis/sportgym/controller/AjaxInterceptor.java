package ru.innopolis.sportgym.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AjaxInterceptor extends LoginUrlAuthenticationEntryPoint {
    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public AjaxInterceptor(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if (request.getHeader("X-AjaxRequest") != null) {
            request.getSession().invalidate();
            response.sendError(401, "");
            System.out.println("Ajax parameter: " + request.getServletPath() + " Header: " + request.getHeader("X-AjaxRequest"));
        } else {
            super.commence(request, response, authException);
            System.out.println("Ajax parameter: " + request.getServletPath() + " Header: " + request.getHeader("X-AjaxRequest"));
        }
    }
}
