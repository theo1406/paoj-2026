package com.pao.project.eticketing.model;

public abstract class Location {
    protected String id;
    protected String name;
    protected int capacity;

    public Location(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return "Location{" + "id='" + id + "', name='" + name + "', capacity=" + capacity + '}';
    }
}