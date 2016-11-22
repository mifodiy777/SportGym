package ru.innopolis.sportgym;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;

import static ru.innopolis.sportgym.config.Constance.DATE_FORMAT;

public class Utils {

    /**
     * Преобразование коллекции элементов в JSON массив с именем aaData для jQuery DataTable
     */
    public static ResponseEntity<String> convertListToJson(GsonBuilder gson, Iterable collection) {
        gson.serializeNulls().setDateFormat(DATE_FORMAT);
        JsonObject obj = new JsonObject();
        obj.add("aaData", gson.create().toJsonTree(collection));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.OK);
    }

    /**
     * Преобразование к обычному типу JSON, не для jQuery DataTable
     *
     * @param gson
     * @param collection
     * @return
     */
    public static String convertJSON(GsonBuilder gson, Iterable collection) {
        return gson.serializeNulls().create().toJsonTree(collection).toString();
    }

}
