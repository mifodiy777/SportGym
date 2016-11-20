package ru.innopolis.sportgym.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static ru.innopolis.sportgym.config.Constance.DATE_FORMAT;

/**
 * Created by Кирилл on 20.11.2016.
 */
public class CalendarAdapter implements JsonSerializer<Calendar> {

    private String format;

    public CalendarAdapter(String format) {
        this.format = format;
    }

    @Override
    public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        if (src != null) {
            jsonObject.addProperty("date", new SimpleDateFormat(format).format(src.getTime()));
        }
        return jsonObject;
    }
}
