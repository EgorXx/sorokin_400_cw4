package ru.itis.kpfu.sorokin.servlet;

import ru.itis.kpfu.sorokin.entity.User;
import ru.itis.kpfu.sorokin.service.UserService;
import ru.itis.kpfu.sorokin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Main", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String sessionUser = (String) session.getAttribute("user");
        if (sessionUser == null) {
            resp.sendRedirect("/login");
        }

        User user = userService.findByLogin(sessionUser);

        String cookieUser = "";
        String sessionId = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("user".equalsIgnoreCase(c.getName())) {
                    cookieUser = c.getValue();
                } else if ("jsessionid".equalsIgnoreCase(c.getName())) {
                    sessionId = c.getValue();
                }
            }
        } else {
            sessionId = session.getId();
        }

        req.setAttribute("sessionUser", sessionUser);
        req.setAttribute("sessionId", sessionId);
        req.setAttribute("cookieUser", cookieUser);
        req.setAttribute("image", user.getImage());
        req.getRequestDispatcher("main.ftl").forward(req, resp);
    }
}
