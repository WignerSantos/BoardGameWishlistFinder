package com.wigner.BoardGameWishlistFinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView displayLoginPage(@RequestParam(value = "error", required = false) String error) {

        ModelAndView modelAndView = new ModelAndView("login.html");

        String errorMessage = null;

        if (error != null) {
            errorMessage = "Usuário ou senha está incorreto!";
        }

        modelAndView.addObject("errorMessage", errorMessage);

        return modelAndView;
    }

}
