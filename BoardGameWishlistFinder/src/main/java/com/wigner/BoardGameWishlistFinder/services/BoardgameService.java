package com.wigner.BoardGameWishlistFinder.services;

import com.wigner.BoardGameWishlistFinder.model.Boardgame;
import com.wigner.BoardGameWishlistFinder.repositories.BoardgameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.Base64;

@Service
public class BoardgameService {

    @Autowired
    BoardgameRepository boardgameRepository;


    /* Codes
    * 1 - Saved
    * 2 - Error
    * 3 - Duplicate
    */
    public int saveBoardgame(Boardgame boardgame) {
        int code = 2;

        String fileName = boardgame.getFile().getOriginalFilename();
        try {
            boardgame.setImageAddress(Base64.getEncoder().encodeToString(boardgame.getFile().getBytes()));
        } catch (IOException e) {
            return 2;
        }

        Boardgame bg = boardgameRepository.readByName(boardgame.getName());
        if(null == bg) {
            boardgameRepository.save(boardgame);
            code = 1;
        } else {
            if(boardgame.getBoardgameId() == bg.getBoardgameId()) {
                if(boardgame.getImageAddress().equals("")) {
                    boardgame.setImageAddress(bg.getImageAddress());
                }

                boardgameRepository.save(boardgame);

                code = 1;
            } else {
              code = 3;
            }
        }

        return code;
    }

}
