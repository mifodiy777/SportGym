package ru.innopolis.sportgym.service;

import ru.innopolis.sportgym.entity.User;

/**
 * Created by Кирилл on 29.10.2016.
 */
public interface UserService {

    boolean addUser(User user);

    boolean deleteUser(Integer id);

    User getUser(Integer id);

    boolean editUser(User user);

    Integer checkPermission(String login, String password);
}
