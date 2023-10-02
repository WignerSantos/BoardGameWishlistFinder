package com.wigner.BoardGameWishlistFinder.controller;

import com.wigner.BoardGameWishlistFinder.model.Person;
import com.wigner.BoardGameWishlistFinder.model.Roles;
import com.wigner.BoardGameWishlistFinder.repositories.RolesRepository;
import com.wigner.BoardGameWishlistFinder.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class UserController {

    @Autowired
    PersonService personService;

    @Autowired
    RolesRepository rolesRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView displayUsersPage() {

        ModelAndView modelAndView = new ModelAndView("users.html");

        List<Person> users = personService.findAll();

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

        boolean isSaved = personService.saveUser(user);
        if(isSaved) {
            modelAndView.setViewName("redirect:/admin/users");
        } else {
            modelAndView.addObject("roles", roles);

            errors.rejectValue("email", "duplicate", "User already exists.");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/displayUserUpdate", method = RequestMethod.GET)
    public ModelAndView displayUserPage(@RequestParam(value = "personId") int personId) {

        ModelAndView modelAndView = new ModelAndView("update_users.html");

        List<Roles> roles = rolesRepository.findAll();
        Person person = personService.findByPersonId(personId);

        if(null == person) {
            modelAndView.setViewName("redirect:/users");
            return modelAndView;
        }

        modelAndView.addObject("user", person);
        modelAndView.addObject("roles", roles);

        return modelAndView;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ModelAndView updateUser(@Valid @ModelAttribute(value = "user") Person user, Errors errors) {

        ModelAndView modelAndView = new ModelAndView("update_users.html");

        List<Roles> roles = rolesRepository.findAll();

        if(errors.hasErrors()) {
            modelAndView.addObject("roles", roles);
            return modelAndView;
        }

        if(user.getPersonId() > 0) {
            boolean isSaved = personService.saveUser(user);

            if(isSaved) {
                modelAndView.setViewName("redirect:/admin/users");
            } else {
                modelAndView.addObject("roles", roles);

                errors.rejectValue("email", "duplicate", "User with the same email already exists.");
            }
        } else {
            modelAndView.addObject("roles", roles);

            errors.reject("notFound", "User not found!");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/deleteUser")
    public ModelAndView deleteUser(@RequestParam(value = "personId") int personId) {

        ModelAndView modelAndView = new ModelAndView("redirect:/admin/users");

        Person person = personService.findByPersonId(personId);
        if(null != person) {
            personService.deleteById(personId);
        }

        return modelAndView;
    }

}
