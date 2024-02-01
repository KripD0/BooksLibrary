package com.example.bookslibrary.service.impl;

import com.example.bookslibrary.db.model.Person;
import com.example.bookslibrary.db.repository.PersonRepository;
import com.example.bookslibrary.exceptions.PersonNotFoundException;
import com.example.bookslibrary.service.mappers.PersonMapper;
import com.example.bookslibrary.web.dto.PersonDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;
    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    @DisplayName("getPeople should be completed")
    void getPeopleShouldBeCompeted() {
        int expectedSize = 3;
        List<Person> people = Arrays.asList(new Person(), new Person(), new Person());

        when(personRepository.findAll()).thenReturn(people);

        List<Person> actualValues = personService.getPeople();

        assertEquals(expectedSize, actualValues.size());
        verify(personRepository).findAll();
    }

    @Test
    @DisplayName("getById should throw exception")
    void getByIdShouldThrowException() {
        when(personRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.getById(UUID.randomUUID()));
    }

    @Test
    @DisplayName("getById should be completed")
    void getByIdShouldBeCompleted() {
        UUID personId = UUID.randomUUID();
        when(personRepository.findById(any())).thenReturn(Optional.of(new Person()));

        Person person = personService.getById(personId);

        assertNotNull(person);
        verify(personRepository).findById(personId);
    }

    @Test
    @DisplayName("createPerson should be completed")
    void createPersonShouldBeCompleted() {
        PersonDto personDto = getPersonDto();
        Person person = new Person();

        when(personMapper.toPerson(any())).thenReturn(person);

        personService.createPerson(personDto);

        verify(personMapper).toPerson(personDto);
        verify(personRepository).save(person);
    }

    @Test
    @DisplayName("editPersonShouldBeCompleted")
    void editPersonShouldBeCompleted() {
        PersonDto personDto = getPersonDto();
        Person person = new Person();
        UUID personId = UUID.randomUUID();

        when(personRepository.findById(any())).thenReturn(Optional.of(person));

        personService.editPerson(personId, personDto);

        verify(personRepository).findById(personId);
        verify(personMapper).updatePerson(person, personDto);
    }

    @Test
    @DisplayName("deletePersonShouldBeCompleted")
    void deletePersonShouldBeCompleted() {
        UUID personId = UUID.randomUUID();

        doNothing().when(personRepository).deleteById(any());

        personService.deletePerson(personId);

        verify(personRepository).deleteById(personId);
    }

    private PersonDto getPersonDto() {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("firstName");
        personDto.setLastName("lastName");
        personDto.setPatronymic("patronymic");
        personDto.setBirthday(LocalDate.now());
        return personDto;
    }
}