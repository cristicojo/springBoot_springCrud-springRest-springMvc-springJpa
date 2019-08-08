package com.example.demo.model;

import com.example.demo.entity.Person;
import com.example.demo.exception.PersonNotFoundException;

public class PersonDto {

    private Long id;
    private boolean employed;
    private String name;
    private int mobile;


    public PersonDto(Long id, boolean employed, String name, int mobile) {

        this.id = id;
        this.employed = employed;
        this.name = name;
        this.mobile = mobile;
    }

    public PersonDto(String message) {

        throw new PersonNotFoundException(message);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public boolean isEmployed() {
        return employed;
    }

    public String getName() {
        return name;
    }

    public int getMobile() {
        return mobile;
    }


    public Person convertTOPerson() {

        if (name.isEmpty())
            throw new IllegalArgumentException("Name should not be null !!!!");

        return new Person(employed, name, mobile);
    }

}
