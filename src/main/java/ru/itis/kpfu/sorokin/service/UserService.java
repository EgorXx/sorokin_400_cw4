package ru.itis.kpfu.sorokin.service;

import ru.itis.kpfu.sorokin.dto.UserDto;
import ru.itis.kpfu.sorokin.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    void save(String name, String lastname, String login, String password, InputStream image) throws IOException;

    User findByLogin(String login);

    boolean loginExist(String login);

    boolean authenticate(String login, String password);
}
