package ru.innopolis.sportgym.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.sportgym.entity.User;

import java.sql.SQLException;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {

    // Получение пользователя по логину(e-mail)
    User findByEmail(String email) throws SQLException;
}
