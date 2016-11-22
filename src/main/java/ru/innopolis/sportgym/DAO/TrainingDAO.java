package ru.innopolis.sportgym.DAO;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.sportgym.entity.Training;
import ru.innopolis.sportgym.entity.TrainingType;
import ru.innopolis.sportgym.entity.User;

import java.util.List;

/**
 * Created by Кирилл on 20.11.2016.
 */
@Repository
public interface TrainingDAO extends JpaRepository<Training, Integer> {
    /**
     * Список тренировок определенного пользователя и типа
     * @param user пользователь
     * @param type тип
     * @return список
     */
    List<Training> findByUserAndType(User user, TrainingType type);

    /**
     * Удаление тренировок удаляемого типа
     * @param trainingType тип
     * @return кол-во удаленных
     */
    Long deleteByType(TrainingType trainingType);

    /**
     * Список тренировок определенного пользователя и выбранного состояния
     * @param user пользователь
     * @param complete состояние
     * @return список
     */
    List<Training> findByUserAndComplete(User user, Boolean complete);

}
