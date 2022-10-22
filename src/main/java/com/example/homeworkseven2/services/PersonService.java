package com.example.homeworkseven2.services;

import com.example.homeworkseven2.models.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PersonService extends UserDetailsService {
    Person addPerson(String firstName, String lastName, String phoneNumber, String username, String password);

    void removePersonById(Long id);

    Person getPersonByUsername(String username);

    Person getPersonById(Long id);

    List<Person> getAllPersons();

    void updatePersonFirstNameByUsername(String username, String firstName);

    void updatePersonLastNameByUsername(String username, String lastName);

    void updatePersonPhoneNumberByUsername(String username, String phoneNumber);
}
