package ru.itis.kpfu.sorokin.servlet;

import ru.itis.kpfu.sorokin.service.UserService;
import ru.itis.kpfu.sorokin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "User", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("users.ftl").forward(req, resp);
    }
}
