package ru.itis.kpfu.sorokin.servlet;

import ru.itis.kpfu.sorokin.service.UserService;
import ru.itis.kpfu.sorokin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.authenticate(login, password)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", login);
            httpSession.setMaxInactiveInterval(60 * 60);

            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(24 * 60 * 60);

            resp.addCookie(cookie);

            resp.sendRedirect("/main");
        } else {
            resp.sendRedirect("/login");
        }

    }

}