package com.example.bookslibrary.db.repository;

import com.example.bookslibrary.db.model.Book;
import com.example.bookslibrary.db.model.LibraryCheckoutRecord;
import com.example.bookslibrary.db.model.Person;
import com.example.bookslibrary.web.rest.dto.LibraryCheckoutRecordDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class LibraryCheckoutRecordRepositoryTest {

    @Autowired
    private LibraryCheckoutRecordRepository libraryCheckoutRecordRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void initDb() {
        Person person = createPerson();
        personRepository.save(person);
        Book book = createBook();
        bookRepository.save(book);
        List<LibraryCheckoutRecord> libraryCheckoutRecords = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            libraryCheckoutRecords.add(createLibraryCheckoutRecord(person, book));
        }
        libraryCheckoutRecordRepository.saveAll(libraryCheckoutRecords);
    }

    //TODO Fix
    @Test
    @DisplayName("findAllLibraryCheckoutRecordsShouldReturnFiveRecordsHasNext")
    void findAllLibraryCheckoutRecords() {
        int pageNumber = 0;
        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Slice<LibraryCheckoutRecordDto> libraryCheckoutRecords = libraryCheckoutRecordRepository.findAllLibraryCheckoutRecords(pageRequest);

        assertEquals(pageNumber, libraryCheckoutRecords.getNumber());
        assertEquals(pageSize, libraryCheckoutRecords.getSize());
    }

    private LibraryCheckoutRecord createLibraryCheckoutRecord(Person person, Book book) {
        return LibraryCheckoutRecord.builder()
                .person(person)
                .book(book)
                .takenDateTime(LocalDateTime.now())
                .build();
    }

    private Person createPerson() {
        return Person.builder()
                .build();
    }

    private Book createBook() {
        return Book.builder()
                .build();
    }
}