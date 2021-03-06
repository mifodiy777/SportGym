package ru.innopolis.sportgym.entity;

import javax.persistence.*;

/**
 * Класс описывающий типы тренировок
 * Created by Кирилл on 24.10.2016.
 */
@Entity
@Table(name = "training_type")
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    //Наименование тренировок
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    /* ----Флаги описывающий в чем и как будет измерятся данный тип тренировок----*/

    //Флаг дистанции
    @Column(name = "distance")
    private Boolean distance;

    //Флаг - время
    @Column(name = "time")
    private Boolean time;

    //Флаг - количество
    @Column(name = "count")
    private Boolean count;

    //Флаг - попытки
    @Column(name = "attempt")
    private Boolean attempt;

    //Флаг - вес
    @Column(name = "weight")
    private Boolean weight;

    //Версия/ оптимистическая блокировка
    @Version
    @Column(name = "version")
    private Long version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getDistance() {
        return distance;
    }

    public void setDistance(Boolean distance) {
        this.distance = distance;
    }

    public Boolean getTime() {
        return time;
    }

    public void setTime(Boolean time) {
        this.time = time;
    }

    public Boolean getCount() {
        return count;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }

    public Boolean getAttempt() {
        return attempt;
    }

    public void setAttempt(Boolean attempt) {
        this.attempt = attempt;
    }

    public Boolean getWeight() {
        return weight;
    }

    public void setWeight(Boolean weight) {
        this.weight = weight;
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

        TrainingType that = (TrainingType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
