package ru.itis.kpfu.sorokin.repository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository {
    private static Map<String, String> mapLoginToPassword;

    static {
        mapLoginToPassword = new HashMap<>();
    }

    public static void signUpUser(String login, String password) {
        mapLoginToPassword.put(login, password);
    }

    public static Map<String, String> getAllUsers() {
        return mapLoginToPassword;
    }
}
