package ru.innopolis.sportgym.editor;

import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.service.UserService;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static ru.innopolis.sportgym.config.Constance.DATE_FORMAT;

/**
 * Created by Кирилл on 20.11.2016.
 */
public class UserEditor extends PropertyEditorSupport {

    private UserService userService;

    public UserEditor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(text.equals("") ? null : userService.getUser(Integer.parseInt(text)));
        } catch (DataSQLException e) {
            setValue(null);
        }
    }

    @Override
    public String getAsText() {
        User user = (User) getValue();
        return (user != null ? user.getId().toString() : "");
    }
}
