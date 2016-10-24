package ru.innopolis.sportgym.entity;

import java.util.Calendar;

/**
 * Created by Кирилл on 24.10.2016.
 */
public class BodyParam {

    private Long id;

    private Calendar measurementDate;

    private Short weight;

    private Short height;

    private Short body;

    private Short haunch;

    private Short forearm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
