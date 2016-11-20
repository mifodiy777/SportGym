package ru.innopolis.sportgym.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.sportgym.entity.TrainingType;
import ru.innopolis.sportgym.entity.User;

import java.util.List;

/**
 * Created by Кирилл on 20.11.2016.
 */
@Repository
public interface TrainingTypeDAO extends JpaRepository<TrainingType, Integer> {

    List<TrainingType> findByUser(User user);
}
