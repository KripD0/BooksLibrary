package com.example.bookslibrary.service.impl;

import com.example.bookslibrary.db.model.Person;
import com.example.bookslibrary.db.repository.PersonRepository;
import com.example.bookslibrary.exceptions.PersonNotFoundException;
import com.example.bookslibrary.service.interfaces.PersonService;
import com.example.bookslibrary.service.mappers.PersonMapper;
import com.example.bookslibrary.web.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Person> getPeoples() {
        log.info("Getting persons");
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Person getById(UUID personId) {
        log.info("Getting person with id: {}", personId);
        return personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException("Person with id: " + personId + " not found."));
    }

    @Override
    public void createPerson(PersonDto personDto) {
        Person person = personMapper.toPerson(personDto);
        personRepository.save(person);
        log.info("Created person with id: {}", person.getId());
    }

    @Override
    public void editPerson(UUID personId, PersonDto personDto) {
        Person person = getById(personId);
        personMapper.updatePerson(person, personDto);
        log.info("Person with id: {} was updated", personId);
    }

    @Override
    public void deletePerson(UUID bookId) {
        personRepository.deleteById(bookId);
        log.info("Person with id: {} was deleted", bookId);
    }
}