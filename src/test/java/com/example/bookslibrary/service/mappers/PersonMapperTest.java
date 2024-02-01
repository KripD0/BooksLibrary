package com.example.bookslibrary.service.mappers;

import com.example.bookslibrary.db.model.Person;
import com.example.bookslibrary.web.dto.PersonDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonMapperTest {

    private final PersonMapperImpl personMapper = new PersonMapperImpl();

    @Test
    @DisplayName("toPerson should map correctly")
    void toPersonShouldBeCompleted() {
        String expectedFirsName = "firstName";
        String expectedLastName = "lastName";
        String expectedPatronymic = "patronymic";
        LocalDate expectedBirthday = LocalDate.now();
        PersonDto personDto = getPersonDto(expectedFirsName, expectedLastName, expectedPatronymic, expectedBirthday);

        Person person = personMapper.toPerson(personDto);

        assertEquals(expectedFirsName, person.getFirstName());
        assertEquals(expectedLastName, person.getLastName());
        assertEquals(expectedPatronymic, person.getPatronymic());
        assertEquals(expectedBirthday, person.getBirthday());
    }

    @Test
    @DisplayName("updatePerson should map correctly")
    void updatePersonShouldBeCompleted() {
        String expectedFirsName = "firstName";
        String expectedLastName = "lastName";
        String expectedPatronymic = "patronymic";
        LocalDate expectedBirthday = LocalDate.now();
        PersonDto personDto = getPersonDto(expectedFirsName, expectedLastName, expectedPatronymic, expectedBirthday);
        Person person = new Person();

        personMapper.updatePerson(person, personDto);

        assertEquals(expectedFirsName, person.getFirstName());
        assertEquals(expectedLastName, person.getLastName());
        assertEquals(expectedPatronymic, person.getPatronymic());
        assertEquals(expectedBirthday, person.getBirthday());
    }

    private PersonDto getPersonDto(String firsName, String lastName, String patronymic, LocalDate birthday) {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName(firsName);
        personDto.setLastName(lastName);
        personDto.setPatronymic(patronymic);
        personDto.setBirthday(birthday);
        return personDto;
    }
}