package ru.innopolis.sportgym.entity;

/**
 * Created by Кирилл on 24.10.2016.
 */
public enum Gender {
    M,W;

    public String getName() {
        switch (this) {
            case M:
                return "Мужской";
            case W:
                return "Женский";
            default:
                return "не определено";
        }
    }
}
