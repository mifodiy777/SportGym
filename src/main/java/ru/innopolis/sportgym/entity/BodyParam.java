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

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

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

    @Version
    @Column(name = "version")
    private Long version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BodyParam bodyParam = (BodyParam) o;

        if (id != null ? !id.equals(bodyParam.id) : bodyParam.id != null) return false;
        if (user != null ? !user.equals(bodyParam.user) : bodyParam.user != null) return false;
        return measurementDate != null ? measurementDate.equals(bodyParam.measurementDate) : bodyParam.measurementDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (measurementDate != null ? measurementDate.hashCode() : 0);
        return result;
    }
}
