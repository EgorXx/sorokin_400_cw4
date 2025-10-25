package ru.itis.kpfu.sorokin.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import ru.itis.kpfu.sorokin.dao.UserDao;
import ru.itis.kpfu.sorokin.dao.impl.UserDaoImpl;
import ru.itis.kpfu.sorokin.dto.UserDto;
import ru.itis.kpfu.sorokin.entity.User;
import ru.itis.kpfu.sorokin.service.UserService;
import ru.itis.kpfu.sorokin.util.CloudinaryUtil;
import ru.itis.kpfu.sorokin.util.PasswordUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    private Cloudinary cloudinary = CloudinaryUtil.getInstance();


    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().
                map(u -> new UserDto(u.getName(), u.getLogin()))
                .toList();
    }

    @Override
    public User findByLogin(String login) {
        userDao.findByLogin(login);
        return userDao.findByLogin(login);
    }

    @Override
    public boolean authenticate(String login, String password) {
        String passwordEncrypt = PasswordUtil.encrypt(password);

        User user = findByLogin(login);

        if (user != null) {
            return user.getPassword().equals(passwordEncrypt);
        }

        return false;
    }

    @Override
    public void save(String name, String lastname, String login, String password, InputStream imageInputStream) throws IOException {
        String passwordEncrypt = PasswordUtil.encrypt(password);

        byte[] bytes = imageInputStream.readAllBytes();

        Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.emptyMap());

        String imageUrl = (String) uploadResult.get("secure_url");

        User user = new User(
                null,
                name,
                lastname,
                login,
                passwordEncrypt,
                imageUrl
        );

        userDao.save(user);
    }

    @Override
    public boolean loginExist(String login) {
        User user = userDao.findByLogin(login);
        return user != null;
    }
}
