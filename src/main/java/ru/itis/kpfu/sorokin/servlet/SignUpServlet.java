package ru.itis.kpfu.sorokin.servlet;

import ru.itis.kpfu.sorokin.service.UserService;
import ru.itis.kpfu.sorokin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class SignUpServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private static final String FILE_PREFFIX = "/Users/egorsorokin/IdeaProjects/sorokin_400_cw4/src/main/webapp";
    public static int DIRECTORIES_COUNT = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sign_up.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Part part = req.getPart("file");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String path = "/images" + File.separator
                + Math.abs(filename.hashCode() % DIRECTORIES_COUNT) + File.separator + filename;

        File file = new File(FILE_PREFFIX + path);

        InputStream content = part.getInputStream();
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];
        content.read(buffer);
        outputStream.write(buffer);
        outputStream.close();

        userService.save(name, lastname, login, password, path);

        resp.sendRedirect("/index");
    }
}