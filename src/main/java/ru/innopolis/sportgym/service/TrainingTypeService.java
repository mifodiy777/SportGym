package ru.innopolis.sportgym.service;

import ru.innopolis.sportgym.entity.TrainingType;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;

import java.util.List;

/**
 * Created by Кирилл on 20.11.2016.
 */
public interface TrainingTypeService  {

    TrainingType getTrainigType(Integer id) throws DataSQLException;

    List<TrainingType> findByUser(User user) throws DataSQLException;

    TrainingType saveTrainigType(TrainingType trainingType) throws DataSQLException;

    void deleteTrainigType(Integer id) throws DataSQLException;


}
