package com.wigner.BoardGameWishlistFinder.controller;

import com.wigner.BoardGameWishlistFinder.model.Person;
import com.wigner.BoardGameWishlistFinder.model.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView displayUsersPage() {

        ModelAndView modelAndView = new ModelAndView("users.html");

        List<Person> users = Arrays.asList(new Person(1, "Wigner", "wigner12@gmail.com", "wigner12@gmail.com", "123456789", "123456789", LocalDateTime.now(), "Wigner", LocalDateTime.now(), "", new Roles(1, "ADMIN")),
                new Person(2, "Wigner2", "wigner13@gmail.com", "wigner13@gmail.com","123456789", "123456789", LocalDateTime.now(), "Wigner", LocalDateTime.now(), "", new Roles(1, "ADMIN")));

        modelAndView.addObject("users", users);

        return modelAndView;
    }

}