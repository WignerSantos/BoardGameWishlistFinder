package com.wigner.BoardGameWishlistFinder.controller;

import com.wigner.BoardGameWishlistFinder.model.Person;
import com.wigner.BoardGameWishlistFinder.model.Roles;
import com.wigner.BoardGameWishlistFinder.repositories.PersonRepository;
import com.wigner.BoardGameWishlistFinder.repositories.RolesRepository;
import com.wigner.BoardGameWishlistFinder.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @Autowired
    RolesRepository rolesRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView displayUsersPage() {

        ModelAndView modelAndView = new ModelAndView("users.html");

        List<Person> users = personRepository.findAll();

        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @RequestMapping(value = "/displayCreateUser", method = RequestMethod.GET)
    public ModelAndView displayCreateUserPage() {

        ModelAndView modelAndView = new ModelAndView("create_users.html");

        List<Roles> roles = rolesRepository.findAll();

        modelAndView.addObject("user", new Person());
        modelAndView.addObject("roles", roles);

        return modelAndView;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ModelAndView createUser(@Valid @ModelAttribute("user") Person user, Errors errors) {

        ModelAndView modelAndView = new ModelAndView("create_users.html");

        List<Roles> roles = rolesRepository.findAll();
        if(errors.hasErrors()) {
            modelAndView.addObject("roles", roles);
            return modelAndView;
        }

        try {
            boolean isSaved = personService.createUser(user);
            if(isSaved) {
                modelAndView.setViewName("redirect:/users");
            } else {
                modelAndView.addObject("roles", roles);
            }
        } catch (Exception e) {
            errors.rejectValue("email", "duplicate", "User with the same email already exists.");
            modelAndView.addObject("roles", roles);
        }
        return modelAndView;
    }

}
