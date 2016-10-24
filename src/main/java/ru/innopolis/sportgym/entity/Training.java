package ru.innopolis.sportgym.entity;

import java.util.Calendar;

/**
 * Created by Кирилл on 24.10.2016.
 */
public class Training {

    private Long  id;

    private TrainingType type;

    private Calendar fromDate;

    private Calendar toDate;

    private Short distance;

    private Short time;

    private Short count;

    private Short attempt;

    private Short weight;

    private Boolean complete;

    private Calendar notificate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public Calendar getFromDate() {
        return fromDate;
    }

    public void setFromDate(Calendar fromDate) {
        this.fromDate = fromDate;
    }

    public Calendar getToDate() {
        return toDate;
    }

    public void setToDate(Calendar toDate) {
        this.toDate = toDate;
    }

    public Short getDistance() {
        return distance;
    }

    public void setDistance(Short distance) {
        this.distance = distance;
    }

    public Short getTime() {
        return time;
    }

    public void setTime(Short time) {
        this.time = time;
    }

    public Short getCount() {
        return count;
    }

    public void setCount(Short count) {
        this.count = count;
    }

    public Short getAttempt() {
        return attempt;
    }

    public void setAttempt(Short attempt) {
        this.attempt = attempt;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Training training = (Training) o;

        if (id != null ? !id.equals(training.id) : training.id != null) return false;
        if (type != null ? !type.equals(training.type) : training.type != null) return false;
        if (fromDate != null ? !fromDate.equals(training.fromDate) : training.fromDate != null) return false;
        if (toDate != null ? !toDate.equals(training.toDate) : training.toDate != null) return false;
        if (distance != null ? !distance.equals(training.distance) : training.distance != null) return false;
        if (time != null ? !time.equals(training.time) : training.time != null) return false;
        if (count != null ? !count.equals(training.count) : training.count != null) return false;
        if (attempt != null ? !attempt.equals(training.attempt) : training.attempt != null) return false;
        if (weight != null ? !weight.equals(training.weight) : training.weight != null) return false;
        if (complete != null ? !complete.equals(training.complete) : training.complete != null) return false;
        return notificate != null ? notificate.equals(training.notificate) : training.notificate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (attempt != null ? attempt.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (complete != null ? complete.hashCode() : 0);
        result = 31 * result + (notificate != null ? notificate.hashCode() : 0);
        return result;
    }
}
