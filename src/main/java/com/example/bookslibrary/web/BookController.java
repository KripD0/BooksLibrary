package com.example.bookslibrary.web;

import com.example.bookslibrary.service.interfaces.BookService;
import com.example.bookslibrary.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String books(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "showBook";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") BookDto bookDto) {
        return "newBook";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.createBook(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("book", bookService.getById(id));
        return "editBook";
    }

    @PutMapping(value = "/{id}")
    public String updateBook(@ModelAttribute("book") BookDto bookDto, @PathVariable("id") UUID id) {
        bookService.editBook(id, bookDto);
        return "redirect:/books";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteBook(@PathVariable("id") UUID id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}