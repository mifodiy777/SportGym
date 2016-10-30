package ru.innopolis.sportgym.DAO.impl;

import ru.innopolis.sportgym.DAO.UserDAO;
import ru.innopolis.sportgym.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Кирилл on 29.10.2016.
 */
public class UserDAOImpl extends MySQLDAO implements UserDAO {

    private final static String ADD_NEW_USER = "INSERT INTO sportgym.users(surname,name,patronymic,email,phone,password,active) VALUES (?,?,?,?,?,?,?)";

    private final static String GET_USER = "SELECT surname,name,patronymic,email,phone,password,active " +
            "FROM sportgym.users WHERE id_users = ?";

    private final static String CHECK_PERMISSION = "SELECT u.id_users AS id FROM users u  WHERE u.email = ? AND u.password = ?";

    private final static String UPDATE_USER = "UPDATE users SET surname = ?, name = ?, patronymic = ?, phone = ? WHERE id_users=?";

    @Override
    public boolean addUser(User user) {
        try (Connection connection = getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER);
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setBoolean(7, user.isActive());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Integer checkPermission(String login, String password) {
        try (Connection connection = getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_PERMISSION);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public User getUser(Integer id) {
        User user = new User();
        user.setId(id);
        try (Connection connection = getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setSurname(resultSet.getString("surname"));
            user.setName(resultSet.getString("name"));
            user.setPatronymic(resultSet.getString("patronymic"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            user.setPassword(resultSet.getString("password"));
            user.setActive(resultSet.getBoolean("active"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean editUser(User user) {
        try (Connection connection = getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteUser(Integer id) {
        return false;
    }


}