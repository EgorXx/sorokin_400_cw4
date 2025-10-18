package ru.itis.kpfu.sorokin.servlet;

import ru.itis.kpfu.sorokin.service.UserService;
import ru.itis.kpfu.sorokin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/ajax/login_exist")
public class LoginExistServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        resp.setContentType("text/plain");

        if (userService.loginExist(login)) {
            resp.getWriter().write("This login is already taken, please enter another one.");
        } else {
            resp.getWriter().write("");
        }
    }
}
