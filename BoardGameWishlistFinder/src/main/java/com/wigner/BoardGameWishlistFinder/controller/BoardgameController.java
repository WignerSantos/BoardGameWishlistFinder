package com.wigner.BoardGameWishlistFinder.controller;

import com.wigner.BoardGameWishlistFinder.model.Boardgame;
import com.wigner.BoardGameWishlistFinder.model.Person;
import com.wigner.BoardGameWishlistFinder.repositories.BoardgameRepository;
import com.wigner.BoardGameWishlistFinder.services.BoardgameService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class BoardgameController {

    @Autowired
    BoardgameRepository boardgameRepository;

    @Autowired
    BoardgameService boardgameService;

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

    @RequestMapping(value = "/admin/createBoardgame", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView createBoardgame(@Valid @ModelAttribute("boardgame") Boardgame boardgame,
                                        Errors errors) {

        ModelAndView modelAndView = new ModelAndView("create_boardgames.html");

        if(errors.hasErrors()) {
            return modelAndView;
        }

        int isSaved = boardgameService.saveBoardgame(boardgame);
        if(isSaved == 1) {
            modelAndView.setViewName("redirect:/boardgames");
        } else if(isSaved == 0) {
            errors.rejectValue("Name", "duplicate", "Boardgame already exists!");
        } else if(isSaved == 2) {
            errors.rejectValue("imageAddress", "null", "Choose an image!");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/boardgame/{boardgameName}")
    public ModelAndView displayABoardgame(@PathVariable("boardgameName") String boardgameName) {

        ModelAndView modelAndView = new ModelAndView("boardgame.html");

        System.out.println(boardgameName);
        Boardgame bg = boardgameRepository.readByName(boardgameName);
        if(null == bg) {
            modelAndView.setViewName("redirect:/boardgames");
        }

        modelAndView.addObject("boardgame", bg);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/deleteBoardgame")
    public ModelAndView deleteBoardgame(@RequestParam(value = "boardgameId") int boardgameId) {

        ModelAndView modelAndView = new ModelAndView("redirect:/boardgames");

        Optional<Boardgame> bg = boardgameRepository.findById(boardgameId);
        if(bg.isPresent()) {
            boardgameRepository.deleteById(boardgameId);
        }

        return modelAndView;

    }

}
