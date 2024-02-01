package com.example.bookslibrary.service.interfaces;

import com.example.bookslibrary.db.model.Person;
import com.example.bookslibrary.web.dto.PersonDto;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    List<Person> getPeople();

    Person getById(UUID personId);

    void createPerson(PersonDto personDto);

    void editPerson(UUID personId, PersonDto personDto);

    void deletePerson(UUID personId);
}