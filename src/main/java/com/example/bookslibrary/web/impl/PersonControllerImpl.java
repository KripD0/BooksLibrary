package com.example.bookslibrary.web.impl;

import com.example.bookslibrary.service.interfaces.PersonService;
import com.example.bookslibrary.web.dto.PersonDto;
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
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonControllerImpl {

    private final PersonService personService;

    @GetMapping
    public String persons(Model model) {
        model.addAttribute("people", personService.getPeoples());
        return "peoples";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("person", personService.getById(id));
        return "showPerson";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") PersonDto personDto) {
        return "newPerson";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") PersonDto personDto) {
        personService.createPerson(personDto);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("person", personService.getById(id));
        return "editPerson";
    }

    @PutMapping(value = "/{id}")
    public String updatePerson(@ModelAttribute("person") PersonDto personDto, @PathVariable("id") UUID id) {
        personService.editPerson(id, personDto);
        return "redirect:/people";
    }

    @DeleteMapping(value = "/{id}")
    public String deletePerson(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }
}