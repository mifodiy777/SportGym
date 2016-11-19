package ru.innopolis.sportgym.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Класс описывающий спортсмена
 * Created by Кирилл on 17.10.2016.
 */
@Entity
@Table(name = "sportsmen")
public class Sportsmen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /*Список измерений физиологических параметров
      Используется для построения графиков*/
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("measurementDate DESC ")
    private Set<BodyParam> bodyParams;

    //Список типов тренировок проводимых данным спортсменом
    @OneToMany(cascade = CascadeType.ALL)
    private Set<TrainingType> trainingTypes;

    //Список тренировок
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Training> trainings;

    @Version
    @Column(name = "version")
    private Long version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

        Sportsmen sportsmen = (Sportsmen) o;

        if (id != null ? !id.equals(sportsmen.id) : sportsmen.id != null) return false;
        if (bodyParams != null ? !bodyParams.equals(sportsmen.bodyParams) : sportsmen.bodyParams != null) return false;
        if (trainingTypes != null ? !trainingTypes.equals(sportsmen.trainingTypes) : sportsmen.trainingTypes != null)
            return false;
        return trainings != null ? trainings.equals(sportsmen.trainings) : sportsmen.trainings == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bodyParams != null ? bodyParams.hashCode() : 0);
        result = 31 * result + (trainingTypes != null ? trainingTypes.hashCode() : 0);
        result = 31 * result + (trainings != null ? trainings.hashCode() : 0);
        return result;
    }
}
