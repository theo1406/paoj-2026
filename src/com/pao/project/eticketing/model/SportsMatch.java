package com.pao.project.eticketing.model;
import java.time.LocalDateTime;

public class SportsMatch extends Event {
    private String sportType;

    public SportsMatch(String id, String name, Location location, LocalDateTime date, String sportType) {
        super(id, name, location, date);
        this.sportType = sportType;
    }
}