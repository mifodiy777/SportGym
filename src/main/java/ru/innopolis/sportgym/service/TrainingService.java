package ru.innopolis.sportgym.service;

import ru.innopolis.sportgym.entity.Training;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;

import java.util.List;

/**
 * Created by Кирилл on 20.11.2016.
 */
public interface TrainingService {

    Training getTraining(Integer id) throws DataSQLException;

    List<Training> findByUser(User user)throws DataSQLException;

    Training saveTraining(Training training) throws DataSQLException;


}
