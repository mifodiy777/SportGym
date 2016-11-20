package ru.innopolis.sportgym.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.sportgym.entity.BodyParam;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;

import java.util.List;

/**
 * Created by Кирилл on 19.11.2016.
 */
@Repository
public interface BodyParamDAO extends JpaRepository<BodyParam, Integer> {

    List<BodyParam> findByUser(User user) throws DataSQLException;
}
