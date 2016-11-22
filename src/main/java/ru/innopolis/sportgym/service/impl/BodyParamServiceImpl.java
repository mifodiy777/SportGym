package ru.innopolis.sportgym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.innopolis.sportgym.DAO.BodyParamDAO;
import ru.innopolis.sportgym.entity.BodyParam;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.service.BodyParamService;

import java.util.List;

/**
 * Created by Кирилл on 19.11.2016.
 */
@Service
public class BodyParamServiceImpl implements BodyParamService {

    private final BodyParamDAO bodyParamDAO;

    @Autowired
    public BodyParamServiceImpl(BodyParamDAO bodyParamDAO) {
        this.bodyParamDAO = bodyParamDAO;
    }

    /* C R U D операции*/

    @Override
    public BodyParam getBodyParam(Integer id) throws DataSQLException {
        try {
            return bodyParamDAO.findOne(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка получение физиологических параметров из базы");
        }
    }

    @Override
    public List<BodyParam> findByUser(User user) throws DataSQLException {
        return bodyParamDAO.findByUser(user);
    }

    @Override
    public BodyParam saveBodyParam(BodyParam bodyParam) throws DataSQLException {
        try {
            return bodyParamDAO.save(bodyParam);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка сохранения физиологичеких параметров");
        }
    }

    @Override
    public void deleteBodyParam(Integer id) throws DataSQLException {
        try {
            bodyParamDAO.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка удаления записи физиологических параметров");
        }
    }
}
