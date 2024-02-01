package com.example.bookslibrary.web;

import com.example.bookslibrary.db.model.LibraryCheckoutRecord;
import com.example.bookslibrary.service.interfaces.BookService;
import com.example.bookslibrary.service.interfaces.LibraryCheckoutRecordService;
import com.example.bookslibrary.service.interfaces.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class LibraryCheckoutController {

    private final LibraryCheckoutRecordService libraryCheckoutRecordService;
    private final PersonService personService;
    private final BookService bookService;

    @GetMapping
    public String takeBook(Model model, @ModelAttribute("checkoutRecord") LibraryCheckoutRecord libraryCheckoutRecord) {
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("people", personService.getPeople());
        return "takeBook";
    }

    @PostMapping
    public String createRecord(@ModelAttribute("checkoutRecord") LibraryCheckoutRecord libraryCheckoutRecord) {
        libraryCheckoutRecordService.createRecord(libraryCheckoutRecord);
        return "redirect:/";
    }
}