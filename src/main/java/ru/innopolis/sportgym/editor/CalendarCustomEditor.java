package ru.innopolis.sportgym.editor;

import org.springframework.format.annotation.DateTimeFormat;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static ru.innopolis.sportgym.config.Constance.DATE_FORMAT;

/**
 * Класс binder даты
 * Created by Кирилл on 19.11.2016.
 */
public class CalendarCustomEditor extends PropertyEditorSupport {

    //Формат даты
    private String format;

    public CalendarCustomEditor(String format) {
        this.format = format;
    }

    @Override
    public String getAsText() {
        Calendar value = (Calendar) getValue();
        return (value != null ? new SimpleDateFormat(format).format(value.getTime()) : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(new SimpleDateFormat(format).parse(text).getTime()));
            setValue(calendar);
        } catch (ParseException e) {
            setValue(null);
        }
    }
}