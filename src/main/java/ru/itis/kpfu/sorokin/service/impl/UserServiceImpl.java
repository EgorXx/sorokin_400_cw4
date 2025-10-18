package ru.itis.kpfu.sorokin.service.impl;

import ru.itis.kpfu.sorokin.dao.UserDao;
import ru.itis.kpfu.sorokin.dao.impl.UserDaoImpl;
import ru.itis.kpfu.sorokin.dto.UserDto;
import ru.itis.kpfu.sorokin.entity.User;
import ru.itis.kpfu.sorokin.service.UserService;
import ru.itis.kpfu.sorokin.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();


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
    public void save(String name, String lastname, String login, String password, String image) {
        String passwordEncrypt = PasswordUtil.encrypt(password);

        User user = new User(
                null,
                name,
                lastname,
                login,
                passwordEncrypt,
                image
        );

        userDao.save(user);
    }

    @Override
    public boolean loginExist(String login) {
        User user = userDao.findByLogin(login);
        return user != null;
    }
}
