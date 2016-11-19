package ru.innopolis.sportgym.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс исключения выбрасываемого при любых ошибках в DAO методах
 * Created by Кирилл on 07.11.2016.
 */
public class DataSQLException extends Exception {

    public DataSQLException(String message) {
        super(message);
    }
}
