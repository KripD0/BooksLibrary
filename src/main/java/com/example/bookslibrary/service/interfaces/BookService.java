package com.example.bookslibrary.service.interfaces;

import com.example.bookslibrary.db.model.Book;
import com.example.bookslibrary.web.dto.BookDto;

import java.util.List;
import java.util.UUID;

public interface BookService {

    List<Book> getBooks();

    Book getById(UUID bookId);

    void createBook(BookDto bookDto);

    void editBook(UUID bookId, BookDto bookDto);

    void deleteBook(UUID bookId);
}