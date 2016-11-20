package ru.innopolis.sportgym.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.innopolis.sportgym.DAO.UserDAO;
import ru.innopolis.sportgym.entity.Role;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Кастомный класс аутентификации
 * Created by Кирилл on 02.11.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.innopolis.sportgym.entity.User user = null;
        try {
            user = userDAO.findByEmail(username.trim());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с таким логином не найден");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        User securityUser = new User(username, user.getPassword(), true, true, true, true, authorities);
        return securityUser;
    }
}