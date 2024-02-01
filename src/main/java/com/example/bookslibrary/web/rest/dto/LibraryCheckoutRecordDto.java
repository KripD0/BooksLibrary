package com.example.bookslibrary.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCheckoutRecordDto {

    private String lastName;
    private String firstName;
    private String patronymic;
    private LocalDate birthday;
    private String title;
    private String author;
    private String ISBN;
    private LocalDateTime takenDateTime;
}