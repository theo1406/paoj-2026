package com.pao.project.eticketing.service;

import com.pao.project.eticketing.exception.EntityNotFoundException;
import com.pao.project.eticketing.model.Event;
import com.pao.project.eticketing.model.Location;

import java.util.*;

public class EventService {
    private static final EventService INSTANCE = new EventService();

    private final Map<String, Location> locations = new HashMap<>();

    private final Set<Event> events = new TreeSet<>();

    private EventService() {}

    public static EventService getInstance() {
        return INSTANCE;
    }

    public void addLocation(Location loc) {
        if (loc != null && loc.getId() != null) {
            locations.put(loc.getId(), loc);
        }
    }

    public void addEvent(Event event) {
        if (event != null && event.getId() != null) {
            events.add(event);
        }
    }

    public void deleteEvent(String eventId) throws EntityNotFoundException {
        Event event = findEventById(eventId);
        events.remove(event);
    }

    public Event findEventById(String id) throws EntityNotFoundException {
        for (Event e : events) {
            if (e.getId().equals(id)) return e;
        }
        throw new EntityNotFoundException("Evenimentul cu ID " + id + " nu exista!");
    }

    public Event findEventByName(String name) throws EntityNotFoundException {
        for (Event e : events) {
            if (e.getName().equalsIgnoreCase(name)) return e;
        }
        throw new EntityNotFoundException("Evenimentul " + name + " nu a fost gasit!");
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    public List<Location> getAllLocations() {
        return new ArrayList<>(locations.values());
    }
}