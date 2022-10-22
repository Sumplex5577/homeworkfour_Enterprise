package com.example.homeworkseven2.controllers;

import com.example.homeworkseven2.dtos.PersonDto;
import com.example.homeworkseven2.models.Person;
import com.example.homeworkseven2.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final PersonService personService;


    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("personForm", new Person());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerPerson(@ModelAttribute("personForm") PersonDto personDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!personDto.getPassword().equals(personDto.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Wrong password");
            return "registration";
        }
        if (personService.addPerson(personDto.getFirstName(), personDto.getLastName(), personDto.getPhoneNumber(),
                personDto.getUsername(), personDto.getPassword()) == null) {
            model.addAttribute("usernameError", "User with this username is already exists");
            return "registration";
        }
//        personService.addPerson(personDto.getFirstName(), personDto.getLastName(), personDto.getPhoneNumber(),
//                personDto.getUsername(), personDto.getPassword());
        return "redirect:/main";
    }

}