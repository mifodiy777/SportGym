package ru.innopolis.sportgym.entity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Класс описывающий тренировки
 * Created by Кирилл on 24.10.2016.
 */
@Entity
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    //Пользователь
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    // Тип тренировки
    @ManyToOne
    @JoinColumn(name = "id_type")
    private TrainingType type;

    // Время проведения
    @Column(name = "target_date")
    private Calendar targetDate;


    // Пройденная дистанция или цель
    @Column(name = "distance")
    private Integer distance;

    // Время тренировки / Временная цель
    @Column(name = "time")
    private Integer time;

    // Количество / Цель
    @Column(name = "count")
    private Integer count;

    // Количество подходов/ Цель
    @Column(name = "attempt")
    private Integer attempt;

    // Вес / Цель
    @Column(name = "weight")
    private Integer weight;

    // Флаг выполнения
    @Column(name = "complete")
    private Boolean complete;

    //Дата/время напоминания(Будильник)
    @Column(name = "notificate")
    private Calendar notificate;

    @Version
    @Column(name = "version")
    private Long version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Calendar targetDate) {
        this.targetDate = targetDate;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Calendar getNotificate() {
        return notificate;
    }

    public void setNotificate(Calendar notificate) {
        this.notificate = notificate;
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

        Training training = (Training) o;

        if (id != null ? !id.equals(training.id) : training.id != null) return false;
        if (user != null ? !user.equals(training.user) : training.user != null) return false;
        return type != null ? type.equals(training.type) : training.type == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
