package ru.itis.kpfu.sorokin.dao.impl;

import ru.itis.kpfu.sorokin.dao.UserDao;
import ru.itis.kpfu.sorokin.entity.User;
import ru.itis.kpfu.sorokin.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public List<User> getAll() {
        String sql = "select * from users";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<User> users = new ArrayList<>();

            if (resultSet != null) {
                while (resultSet.next()) {
                    users.add(
                            new User(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("lastname"),
                                    resultSet.getString("login"),
                                    resultSet.getString("password"),
                                    resultSet.getString("image")
                            )
                    );
                }
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        String sql = "insert into users (name, lastname, login, password, image) values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User findByLogin(String login) {
        String sql = "select id, name, lastname, login, password, image from users where login=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("image")
                );
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(Integer id) {
        return null;
    }
}
