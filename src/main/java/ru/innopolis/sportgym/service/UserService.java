package ru.innopolis.sportgym.service;

import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;

/**
 * Created by Кирилл on 19.11.2016.
 */
public interface UserService {

    User saveProfile(User user) throws DataSQLException;

}
