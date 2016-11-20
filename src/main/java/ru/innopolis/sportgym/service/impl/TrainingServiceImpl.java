package ru.innopolis.sportgym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.innopolis.sportgym.DAO.TrainingDAO;
import ru.innopolis.sportgym.DAO.TrainingTypeDAO;
import ru.innopolis.sportgym.entity.Training;
import ru.innopolis.sportgym.entity.TrainingType;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.service.TrainingService;
import ru.innopolis.sportgym.service.TrainingTypeService;

import java.util.List;

/**
 * Created by Кирилл on 20.11.2016.
 */
@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingDAO trainingDAO;

    @Autowired
    public TrainingServiceImpl(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public Training getTraining(Integer id) throws DataSQLException {
        try {
            return trainingDAO.findOne(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка получения тренировки из базы");

        }
    }

    @Override
    public List<Training> findByUser(User user) throws DataSQLException {
        try {
            return trainingDAO.findByUser(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка получения  списка тренировок");
        }
    }

    @Override
    public Training saveTraining(Training training) throws DataSQLException {
        try {
            return trainingDAO.save(training);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка сохранения тренировки в базу");
        }
    }
}
