package com.wigner.BoardGameWishlistFinder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.apache.logging.log4j.message.Message;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Boardgame extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int boardgameId;

    @NotBlank(message = "Name must not be blank!")
    private String name;

    @NotBlank(message = "Publishing must not be blank!")
    private String publishing;

    @NotBlank(message = "Duration must not be blank!")
    private String duration;

    @NotBlank(message = "Players must not be blank!")
    private String players;

    @NotBlank(message = "Sleeve Size must not be blank!")
    private String sleeveSize;

    @NotBlank(message = "Image address must not be blank")
    private String imageAddress;

    public Boardgame() {}

    public int getBoardgameId() {
        return boardgameId;
    }

    public void setBoardgameId(int boardgameId) {
        this.boardgameId = boardgameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getSleeveSize() {
        return sleeveSize;
    }

    public void setSleeveSize(String sleeveSize) {
        this.sleeveSize = sleeveSize;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}
