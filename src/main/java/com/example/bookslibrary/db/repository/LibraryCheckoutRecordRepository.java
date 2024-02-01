package com.example.bookslibrary.db.repository;

import com.example.bookslibrary.db.model.LibraryCheckoutRecord;
import com.example.bookslibrary.web.rest.dto.LibraryCheckoutRecordDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface LibraryCheckoutRecordRepository extends JpaRepository<LibraryCheckoutRecord, UUID> {

    @Query("SELECT new com.example.bookslibrary.web.rest.dto.LibraryCheckoutRecordDto(" +
           "p.lastName, p.firstName, p.patronymic, p.birthday, b.title, b.author, b.ISBN, l.takenDateTime) " +
           "FROM LibraryCheckoutRecord l " +
           "JOIN l.person p " +
           "JOIN l.book b ")
    Slice<LibraryCheckoutRecordDto> findAllLibraryCheckoutRecords(Pageable pageable);
}