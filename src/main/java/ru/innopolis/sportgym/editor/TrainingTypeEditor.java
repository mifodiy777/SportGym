package ru.innopolis.sportgym.editor;

import ru.innopolis.sportgym.entity.TrainingType;
import ru.innopolis.sportgym.entity.User;
import ru.innopolis.sportgym.exception.DataSQLException;
import ru.innopolis.sportgym.service.TrainingTypeService;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * Created by Кирилл on 20.11.2016.
 */
public class TrainingTypeEditor extends PropertyEditorSupport {

    private TrainingTypeService typeService;

    public TrainingTypeEditor(TrainingTypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(text.equals("") ? null : typeService.getTrainigType(Integer.parseInt(text)));
        } catch (DataSQLException e) {
            setValue(null);
        }
    }

    @Override
    public String getAsText() {
        TrainingType type = (TrainingType) getValue();
        return (type != null ? type.getId().toString() : "");
    }

}
