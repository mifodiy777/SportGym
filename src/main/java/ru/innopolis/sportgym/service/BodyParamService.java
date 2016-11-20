package ru.innopolis.sportgym.service;

import ru.innopolis.sportgym.entity.BodyParam;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;

import java.util.List;

/**
 * Created by Кирилл on 19.11.2016.
 */
public interface BodyParamService {

    BodyParam getBodyParam(Integer id) throws DataSQLException;

    List<BodyParam> findByUser(User user) throws DataSQLException;

    BodyParam saveBodyParam(BodyParam bodyParam) throws DataSQLException;

    void deleteBodyParam(Integer id) throws DataSQLException;
}
