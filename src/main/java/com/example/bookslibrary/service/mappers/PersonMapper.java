package com.example.bookslibrary.service.mappers;

import com.example.bookslibrary.db.model.Person;
import com.example.bookslibrary.web.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toPerson(PersonDto personDto);

    void updatePerson(@MappingTarget Person person, PersonDto personDto);
}