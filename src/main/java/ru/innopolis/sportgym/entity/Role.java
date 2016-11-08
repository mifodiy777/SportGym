package ru.innopolis.sportgym.entity;

import javax.persistence.*;

/**
 * Класс описывающий роль пользователя системы
 * Created by Кирилл on 02.11.2016.
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    //Роль (ROLE_USER)
    @Column(name = "name", unique = true)
    private String name;

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
}