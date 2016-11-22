package ru.innopolis.sportgym.service;

import ru.innopolis.sportgym.entity.Training;
import ru.innopolis.sportgym.entity.TrainingType;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Кирилл on 20.11.2016.
 */
public interface TrainingService {

    Training getTraining(Integer id) throws DataSQLException;

    List<Training> findByUserAndType(User user, TrainingType type) throws DataSQLException;

    Training saveTraining(Training training) throws DataSQLException;

    Calendar getNotificate(Calendar target, String alarm);

    void deleteTraining(Integer id) throws DataSQLException;

    List<Training> findByUserAndComplete(User user, Boolean complete) throws DataSQLException;


}
