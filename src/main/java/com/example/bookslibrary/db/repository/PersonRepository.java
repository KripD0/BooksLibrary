package com.example.bookslibrary.db.repository;

import com.example.bookslibrary.db.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

}