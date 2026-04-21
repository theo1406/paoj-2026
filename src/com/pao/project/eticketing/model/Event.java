package com.pao.project.eticketing.model;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Event implements Comparable<Event> {
    protected String id;
    protected String name;
    protected Location location;
    protected LocalDateTime date;
    protected int availableSeats;

    public Event(String id, String name, Location location, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.availableSeats = location.getCapacity();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public LocalDateTime getDate() { return date; }
    public int getAvailableSeats() { return availableSeats; }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public int compareTo(Event o) {
        int dateCompare = this.date.compareTo(o.date);
        return (dateCompare != 0) ? dateCompare : this.id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s la %s (Locuri libere: %d)", date, name, location.getName(), availableSeats);
    }
}