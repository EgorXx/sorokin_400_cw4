package ru.itis.kpfu.sorokin.dao;

import ru.itis.kpfu.sorokin.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    void save(User user);

    User getById(Integer id);

    User findByLogin(String login);
}
