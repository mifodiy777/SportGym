package ru.innopolis.sportgym.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.innopolis.sportgym.entity.User;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/**
 * Класс конвертер JSON
 * Created by Кирилл on 22.11.2016.
 */
public class UserAdapter implements JsonSerializer<User> {

    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        if (src != null) {
            jsonObject.addProperty("id", src.getId());
        }
        return jsonObject;
    }
}
