package ru.innopolis.sportgym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.sportgym.DAO.RoleDAO;
import ru.innopolis.sportgym.DAO.UserDAO;
import ru.innopolis.sportgym.entity.Role;
import ru.innopolis.sportgym.entity.Sportsmen;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.service.UserService;

/**
 * Created by Кирилл on 19.11.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    private RoleDAO roleDAO;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User saveProfile(User user) throws DataSQLException {
        user.setSportsmen(new Sportsmen());
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        for (Role role : roleDAO.findAll()) {
            if (role.getName().equals("ROLE_USER")) {
               user.setRole(role);
            }
        }
        try {
            return userDAO.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка сохранения нового пользователя");
        }


    }
}
