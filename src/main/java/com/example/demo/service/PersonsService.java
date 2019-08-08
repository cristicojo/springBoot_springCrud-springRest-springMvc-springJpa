package com.example.demo.service;

import com.example.demo.model.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.PersonsRepository;
import com.example.demo.entity.Person;
import com.example.demo.exception.PersonNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PersonsService {

    @Autowired
    PersonsRepository repository;


    public PersonDto add(PersonDto personDto) {

        Person save = repository.save(personDto.convertTOPerson());

        return save.convertToDTO();
    }


    public List<PersonDto> findAll() {

        List<Person> personList = repository.findAll();

        List<PersonDto> personDtos = new ArrayList<>();

        for (Person p : personList) {

            personDtos.add(new PersonDto(p.getId(), p.isEmployed(), p.getName(), p.getMobile()));
        }

        return personDtos;
    }


    public PersonDto findPersonById(long id) {

        Optional<Person> optionalPerson = repository.findById(id);

        if (optionalPerson.isPresent()) {

            return (new PersonDto(optionalPerson.get().getId(), optionalPerson.get().isEmployed(), optionalPerson.get().getName(), optionalPerson.get().getMobile()));
        }

        return new PersonDto("Couldn't find a Person with id: " + id);

    }

    public PersonDto updatePerson(PersonDto personDto) {

        Optional<Person> optionalPerson = repository.findById(personDto.getId());

        if (optionalPerson.isPresent()) {

            Person p = optionalPerson.get().updateFromDTO(personDto);

            Person saved = repository.save(p);

            return saved.convertToDTO();

        } else {

            throw new PersonNotFoundException("Could not find a Person with id " + personDto.getId());
        }

    }


    public void deleteAll() {
        repository.deleteAll();
    }

    public void deletePersonById(long id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);

        } else
            throw new PersonNotFoundException("Couldn't find a Person with id: " + id);

    }


}
