package com.example.bookslibrary.service.impl;

import com.example.bookslibrary.db.model.Book;
import com.example.bookslibrary.db.repository.BookRepository;
import com.example.bookslibrary.exceptions.BookNotFoundException;
import com.example.bookslibrary.service.interfaces.BookService;
import com.example.bookslibrary.service.mappers.BookMapper;
import com.example.bookslibrary.web.dto.BookDto;
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
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooks() {
        log.info("Getting books");
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Book getById(UUID bookId) {
        log.info("Getting book with id: {}", bookId);
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " not found."));
    }

    @Override
    public void createBook(BookDto bookDto) {
        Book book = bookMapper.toBook(bookDto);
        bookRepository.save(book);
        log.info("Created book with id: {}", book.getId());
    }

    @Override
    public void editBook(UUID bookId, BookDto bookDto) {
        Book book = getById(bookId);
        bookMapper.updateBook(book, bookDto);
        log.info("Book with id: {} was updated", bookId);
    }

    @Override
    public void deleteBook(UUID bookId) {
        bookRepository.deleteById(bookId);
        log.info("Book with id: {} was deleted", bookId);
    }
}