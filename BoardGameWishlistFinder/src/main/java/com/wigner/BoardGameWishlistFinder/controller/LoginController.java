package com.wigner.BoardGameWishlistFinder.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                         @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView modelAndView = new ModelAndView("login.html");

        String errorMessage = null;

        if (error != null) {
            errorMessage = "Usuário ou senha está incorreto!";
        } else if (logout != null) {
            errorMessage = "Você foi desconectado com sucesso!";
        }

        modelAndView.addObject("errorMessage", errorMessage);

        return modelAndView;
    }

    @RequestMapping(value = "/logout" , method = RequestMethod.GET)
    public ModelAndView logoutPage(HttpServletRequest request,
                                   HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return new ModelAndView("redirect:/login?logout=true");
    }

}
