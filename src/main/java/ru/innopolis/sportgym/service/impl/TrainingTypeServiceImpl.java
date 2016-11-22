package ru.innopolis.sportgym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.sportgym.DAO.TrainingDAO;
import ru.innopolis.sportgym.DAO.TrainingTypeDAO;
import ru.innopolis.sportgym.entity.TrainingType;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.service.TrainingTypeService;

import java.util.List;

/**
 * Created by Кирилл on 20.11.2016.
 */
@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private final TrainingTypeDAO trainingTypeDAO;

    private final TrainingDAO trainingDAO;

    @Autowired
    public TrainingTypeServiceImpl(TrainingTypeDAO trainingTypeDAO, TrainingDAO trainingDAO) {
        this.trainingTypeDAO = trainingTypeDAO;
        this.trainingDAO = trainingDAO;
    }

    @Override
    public TrainingType getTrainingType(Integer id) throws DataSQLException {
        try {
            return trainingTypeDAO.findOne(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка получения типа тренировок");
        }

    }

    @Override
    public List<TrainingType> findByUser(User user) throws DataSQLException {
        try {
            return trainingTypeDAO.findByUser(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка получения списка типов тренировок текущего пользователя");
        }
    }

    @Override
    public TrainingType saveTrainingType(TrainingType trainingType) throws DataSQLException {
        try {
            return trainingTypeDAO.save(trainingType);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка сохранения типа тренировок");
        }
    }

    @Override
    @Transactional
    public void deleteTrainingType(Integer id) throws DataSQLException {
        try {
            TrainingType trainingType = trainingTypeDAO.findOne(id);
            trainingDAO.deleteByType(trainingType);
            trainingTypeDAO.delete(trainingType);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка удаление типа тренировок");
        }
    }
}
