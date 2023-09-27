package com.wigner.BoardGameWishlistFinder.services;

import com.wigner.BoardGameWishlistFinder.model.Person;
import com.wigner.BoardGameWishlistFinder.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean createUser(Person person) {

        boolean isSaved = false;

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);

        if(null != person && person.getPersonId() > 0) {
            isSaved = true;
        }

        return isSaved;
    }

}
