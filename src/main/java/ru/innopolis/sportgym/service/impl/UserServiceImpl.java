package ru.innopolis.sportgym.service.impl;

import ru.innopolis.sportgym.DAO.UserDAO;
import ru.innopolis.sportgym.DAO.impl.UserDAOImpl;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.service.UserService;

/**
 * Created by Кирилл on 29.10.2016.
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public User getUser(Integer id) {
        return userDAO.getUser(id);
    }

    @Override
    public boolean editUser(User user) {
       return userDAO.editUser(user);
    }

    @Override
    public Integer checkPermission(String login, String password) {
        return userDAO.checkPermission(login, password);
    }
}
