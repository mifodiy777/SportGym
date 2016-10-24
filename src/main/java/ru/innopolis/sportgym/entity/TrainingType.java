package ru.innopolis.sportgym.entity;

/**
 * Created by Кирилл on 24.10.2016.
 */
public class TrainingType {

    private Long id;

    private String name;

    private Boolean distance;

    private Boolean time;

    private Boolean count;

    private Boolean attempt;

    private Boolean weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainingType that = (TrainingType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (distance != null ? !distance.equals(that.distance) : that.distance != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (attempt != null ? !attempt.equals(that.attempt) : that.attempt != null) return false;
        return weight != null ? weight.equals(that.weight) : that.weight == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (attempt != null ? attempt.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}
