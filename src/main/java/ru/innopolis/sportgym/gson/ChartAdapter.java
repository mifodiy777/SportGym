package ru.innopolis.sportgym.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.innopolis.sportgym.entity.BodyParam;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static ru.innopolis.sportgym.config.Constance.DATE_FORMAT;

/**
 * Класс конвертер JSON
 * Created by Кирилл on 20.11.2016.
 */
public class ChartAdapter implements JsonSerializer<BodyParam> {

    private String format;

    public ChartAdapter(String format) {
        this.format = format;
    }

    @Override
    public JsonElement serialize(BodyParam src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        if (src != null) {
            jsonObject.addProperty("year", new SimpleDateFormat("yyyy-MM-dd").format(src.getMeasurementDate().getTime()));
            switch (format) {
                case "weight":
                    jsonObject.addProperty("value", src.getWeight());
                    break;
                case "height":
                    jsonObject.addProperty("value", src.getHeight());
                    break;
                case "body":
                    jsonObject.addProperty("value", src.getBody());
                    break;
                case "haunch":
                    jsonObject.addProperty("value", src.getHaunch());
                    break;
                case "forearm":
                    jsonObject.addProperty("value", src.getForearm());
                    break;
            }

        }
        return jsonObject;
    }
}
