package com.example.bookslibrary.service.impl;

import com.example.bookslibrary.db.model.Book;
import com.example.bookslibrary.db.repository.BookRepository;
import com.example.bookslibrary.exceptions.BookNotFoundException;
import com.example.bookslibrary.service.mappers.BookMapper;
import com.example.bookslibrary.web.dto.BookDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("getBooks should be completed")
    void getBooksShouldBeCompleted() {
        int expectedListSize = 2;
        when(bookRepository.findAll()).thenReturn(Arrays.asList(new Book(), new Book()));

        List<Book> books = bookService.getBooks();

        assertEquals(expectedListSize, books.size());
        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("getById should throw exception")
    void getByIdShouldThrowException() {
        when(bookRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getById(UUID.randomUUID()));
    }

    @Test
    @DisplayName("getById should be completed")
    void getByIdShouldBeCompleted() {
        UUID bookId = UUID.randomUUID();

        when(bookRepository.findById(any())).thenReturn(Optional.of(new Book()));

        Book book = bookService.getById(bookId);

        assertNotNull(book);
        verify(bookRepository).findById(bookId);
    }

    @Test
    @DisplayName("createBook should be completed")
    void createBookShouldBeCompleted() {
        BookDto bookDto = getBookDto();
        Book book = new Book();

        when(bookMapper.toBook(any())).thenReturn(book);

        bookService.createBook(bookDto);

        verify(bookMapper).toBook(bookDto);
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("editBook should be completed")
    void editBookShouldBeCompleted() {
        BookDto bookDto = getBookDto();
        Book book = new Book();
        UUID bookId = UUID.randomUUID();

        doNothing().when(bookMapper).updateBook(any(), any());
        when(bookRepository.findById(any())).thenReturn(Optional.of(book));

        bookService.editBook(bookId, bookDto);

        verify(bookRepository).findById(bookId);
        verify(bookMapper).updateBook(book, bookDto);
    }

    @Test
    @DisplayName("deleteBook should be completed")
    void deleteBookShouldBeCompleted() {
        UUID bookId = UUID.randomUUID();

        bookService.deleteBook(bookId);

        verify(bookRepository).deleteById(bookId);

    }

    private BookDto getBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("title");
        bookDto.setAuthor("author");
        bookDto.setISBN("isbn");
        return bookDto;
    }
}