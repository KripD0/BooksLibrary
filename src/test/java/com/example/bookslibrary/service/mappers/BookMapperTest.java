package com.example.bookslibrary.service.mappers;

import com.example.bookslibrary.db.model.Book;
import com.example.bookslibrary.web.dto.BookDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {

    private final BookMapperImpl bookMapper = new BookMapperImpl();

    @Test
    @DisplayName("toBook should map correctly")
    void toBookShouldBeCompleted() {
        String expectedTitle = "title";
        String expectedAuthor = "author";
        String expectedISBN = "isbn";
        BookDto bookDto = getBookDto(expectedTitle, expectedAuthor, expectedISBN);

        Book book = bookMapper.toBook(bookDto);

        assertEquals(expectedTitle, book.getTitle());
        assertEquals(expectedAuthor, book.getAuthor());
        assertEquals(expectedISBN, book.getISBN());
    }

    @Test
    @DisplayName("updateBook should map correctly")
    void updateBookShouldBeCompleted() {
        String expectedTitle = "title";
        String expectedAuthor = "author";
        String expectedISBN = "isbn";
        BookDto bookDto = getBookDto(expectedTitle, expectedAuthor, expectedISBN);
        Book book = new Book();

        bookMapper.updateBook(book, bookDto);

        assertEquals(expectedTitle, book.getTitle());
        assertEquals(expectedAuthor, book.getAuthor());
        assertEquals(expectedISBN, book.getISBN());
    }

    private BookDto getBookDto(String title, String author, String isbn) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(title);
        bookDto.setAuthor(author);
        bookDto.setISBN(isbn);
        return bookDto;
    }
}