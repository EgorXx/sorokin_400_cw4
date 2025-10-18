package ru.itis.kpfu.sorokin.service;

import ru.itis.kpfu.sorokin.dto.UserDto;
import ru.itis.kpfu.sorokin.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    void save(String name, String lastname, String login, String password, String image);

    User findByLogin(String login);

    boolean loginExist(String login);

    boolean authenticate(String login, String password);
}
