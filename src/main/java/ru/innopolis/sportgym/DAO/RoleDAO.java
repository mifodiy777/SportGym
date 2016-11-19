package ru.innopolis.sportgym.DAO;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.sportgym.entity.Role;
import ru.innopolis.sportgym.entity.User;

/**
 * Created by Кирилл on 19.11.2016.
 */
public interface RoleDAO extends CrudRepository<Role, Integer> {
}
