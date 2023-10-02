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

    public boolean saveUser(Person person) {

        boolean isSaved = false;

        person.setPassword(passwordEncoder.encode(person.getPassword()));

        Person p1 = personRepository.readByEmail(person.getEmail());
        if(null == p1) {
            personRepository.save(person);

            if(person.getPersonId() > 0) {
                isSaved = true;
            }
        } else {
            if(person.getPersonId() == p1.getPersonId()) {
                personRepository.save(person);

                isSaved = true;
            }
        }

        return isSaved;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findByPersonId(int personId) {
        return personRepository.findByPersonId(personId);
    }

    public void deleteById(int id) {
        personRepository.deleteById(id);
    }

}
