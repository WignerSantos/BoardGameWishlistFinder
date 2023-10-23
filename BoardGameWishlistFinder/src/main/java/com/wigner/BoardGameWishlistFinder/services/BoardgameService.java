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

    @Value("${boardgame.images.source}")
    private String boardgameImages;

    @Autowired
    BoardgameRepository boardgameRepository;


    /* Codes
    * 0 - NotSaved
    * 1 - Saved
    * 2 - ImageNull
    * 3 - Error
    */
    public int saveBoardgame(Boardgame boardgame) {
        int code = 0;

        String fileName = boardgame.getFile().getOriginalFilename();
        try {
            boardgame.setImageAddress(Base64.getEncoder().encodeToString(boardgame.getFile().getBytes()));
        } catch (IOException e) {
            return 3;
        }

        if(boardgame.getImageAddress().equals("")) {
            return 2;
        }

        Boardgame bg = boardgameRepository.readByName(boardgame.getName());
        if(null == bg) {
            boardgameRepository.save(boardgame);
            code = 1;
        } else {
            if(boardgame.getBoardgameId() == bg.getBoardgameId()) {
                boardgameRepository.save(boardgame);

                code = 1;
            }
        }

        return code;
    }

}
