package com.example.demo.controller;

import com.example.demo.model.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.PersonsService;
import com.example.demo.entity.Person;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PersonsController {

    @Autowired
    private PersonsService service;

    @GetMapping("/persons")
    public List<PersonDto> getAllPersons() {
        return service.findAll();
    }


    @GetMapping("/person/{id}")
    public PersonDto getPersonById(@PathVariable(value = "id") long id) {

        return service.findPersonById(id);

    }


    @PostMapping("/persons")
    public PersonDto createPerson(@RequestBody PersonDto personDto) {

        return service.add(personDto);

    }


    @PutMapping("/person")
    public PersonDto updatePerson(@RequestBody PersonDto personDtoDetails) {

        return service.updatePerson(personDtoDetails);

    }


    @DeleteMapping("person/{id}")
    public ResponseEntity<Person> deletePersonById(@PathVariable(value = "id") Long id) {

        service.findPersonById(id);

        service.deletePersonById(id);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/persons")
    public void deleteAll() {

        service.deleteAll();

    }

}
