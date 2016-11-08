package ru.innopolis.sportgym.entity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Класс описывающий физиологические параметры
 * Created by Кирилл on 24.10.2016.
 */
@Entity
@Table(name = "body_param")
public class BodyParam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    //Дата измерения
    @Column(name = "measurement_date")
    private Calendar measurementDate;

    //Вес /кг
    @Column(name = "weight")
    private Short weight;

    //Рост /см
    @Column(name = "height")
    private Short height;

    //Окружность груди /см
    @Column(name = "body")
    private Short body;

    //Окружность бедра /см
    @Column(name = "haunch")
    private Short haunch;

    //Окружность предплечья
    @Column(name = "forearm")
    private Short forearm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Calendar measurementDate) {
        this.measurementDate = measurementDate;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public Short getBody() {
        return body;
    }

    public void setBody(Short body) {
        this.body = body;
    }

    public Short getHaunch() {
        return haunch;
    }

    public void setHaunch(Short haunch) {
        this.haunch = haunch;
    }

    public Short getForearm() {
        return forearm;
    }

    public void setForearm(Short forearm) {
        this.forearm = forearm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BodyParam bodyParam = (BodyParam) o;

        if (id != null ? !id.equals(bodyParam.id) : bodyParam.id != null) return false;
        if (measurementDate != null ? !measurementDate.equals(bodyParam.measurementDate) : bodyParam.measurementDate != null)
            return false;
        if (weight != null ? !weight.equals(bodyParam.weight) : bodyParam.weight != null) return false;
        if (height != null ? !height.equals(bodyParam.height) : bodyParam.height != null) return false;
        if (body != null ? !body.equals(bodyParam.body) : bodyParam.body != null) return false;
        if (haunch != null ? !haunch.equals(bodyParam.haunch) : bodyParam.haunch != null) return false;
        return forearm != null ? forearm.equals(bodyParam.forearm) : bodyParam.forearm == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (measurementDate != null ? measurementDate.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (haunch != null ? haunch.hashCode() : 0);
        result = 31 * result + (forearm != null ? forearm.hashCode() : 0);
        return result;
    }
}
