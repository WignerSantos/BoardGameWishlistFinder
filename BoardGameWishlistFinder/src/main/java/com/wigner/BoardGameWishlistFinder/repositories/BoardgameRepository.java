package com.wigner.BoardGameWishlistFinder.repositories;

import com.wigner.BoardGameWishlistFinder.model.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardgameRepository extends JpaRepository<Boardgame, Integer> {

    Boardgame readByName(String name);

}
