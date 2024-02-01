package com.example.bookslibrary.service.mappers;

import com.example.bookslibrary.db.model.Book;
import com.example.bookslibrary.web.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toBook(BookDto bookDto);

    void updateBook(@MappingTarget Book book, BookDto bookDto);
}