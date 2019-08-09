package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.model.PersonDto;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;


    private boolean employed;
    private String name;
    private int mobile;


    public Person(boolean employed, String name, int mobile) {

        this.employed = employed;
        this.name = name;
        this.mobile = mobile;

    }

    public Person() {
    }

    public PersonDto convertToDTO() {
        return new PersonDto(id, employed, name, mobile);
    }


    public Person updateFromDTO(PersonDto personDto) {

        if (this.employed != personDto.isEmployed()) {
            this.employed = personDto.isEmployed();
        }

        if (!(this.name.equals(personDto.getName()))) {
            this.name = personDto.getName();
        }

        if (this.mobile != personDto.getMobile()) {
            this.mobile = personDto.getMobile();
        }

        return this;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

}
