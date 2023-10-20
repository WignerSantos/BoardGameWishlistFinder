package com.wigner.BoardGameWishlistFinder.controller;

import com.wigner.BoardGameWishlistFinder.model.Boardgame;
import com.wigner.BoardGameWishlistFinder.repositories.BoardgameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardgameController {

    @Autowired
    BoardgameRepository boardgameRepository;

    @RequestMapping(value = "/boardgames", method = RequestMethod.GET)
    public ModelAndView displayBoardgamesPage() {

        ModelAndView modelAndView = new ModelAndView("boardgames.html");

        List<Boardgame> boardgameList = boardgameRepository.findAll();

        modelAndView.addObject("boardgames", boardgameList);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/displayCreateBoardgame", method = RequestMethod.GET)
    public ModelAndView displayCreateBoardgame() {

        ModelAndView modelAndView = new ModelAndView("create_boardgames.html");

        modelAndView.addObject("boardgame", new Boardgame());

        return modelAndView;
    }

}
