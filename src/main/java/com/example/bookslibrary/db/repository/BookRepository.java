package com.example.bookslibrary.db.repository;

import com.example.bookslibrary.db.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

}