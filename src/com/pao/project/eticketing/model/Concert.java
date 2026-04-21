package com.pao.project.eticketing.model;
import java.time.LocalDateTime;

public class Concert extends Event {
    private String artist;

    public Concert(String id, String name, Location location, LocalDateTime date, String artist) {
        super(id, name, location, date);
        this.artist = artist;
    }
}