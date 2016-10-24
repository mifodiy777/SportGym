package ru.innopolis.sportgym.entity;

import java.util.List;
import java.util.Set;

/**
 * Created by Кирилл on 17.10.2016.
 */
public class Sportsmen {

    private Long id;

    private User user;

    private Gender gender;

    private Set<BodyParam> bodyParams;

    private Set<TrainingType> trainingTypes;

    private Set<Training> trainings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<BodyParam> getBodyParams() {
        return bodyParams;
    }

    public void setBodyParams(Set<BodyParam> bodyParams) {
        this.bodyParams = bodyParams;
    }

    public Set<TrainingType> getTrainingTypes() {
        return trainingTypes;
    }

    public void setTrainingTypes(Set<TrainingType> trainingTypes) {
        this.trainingTypes = trainingTypes;
    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sportsmen sportsmen = (Sportsmen) o;

        if (id != null ? !id.equals(sportsmen.id) : sportsmen.id != null) return false;
        if (user != null ? !user.equals(sportsmen.user) : sportsmen.user != null) return false;
        if (gender != sportsmen.gender) return false;
        if (bodyParams != null ? !bodyParams.equals(sportsmen.bodyParams) : sportsmen.bodyParams != null) return false;
        if (trainingTypes != null ? !trainingTypes.equals(sportsmen.trainingTypes) : sportsmen.trainingTypes != null)
            return false;
        return trainings != null ? trainings.equals(sportsmen.trainings) : sportsmen.trainings == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (bodyParams != null ? bodyParams.hashCode() : 0);
        result = 31 * result + (trainingTypes != null ? trainingTypes.hashCode() : 0);
        result = 31 * result + (trainings != null ? trainings.hashCode() : 0);
        return result;
    }
}
