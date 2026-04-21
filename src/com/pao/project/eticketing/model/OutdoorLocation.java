package com.pao.project.eticketing.model;

public class OutdoorLocation extends Location {
    private boolean isCovered;

    public OutdoorLocation(String id, String name, int capacity, boolean isCovered) {
        super(id, name, capacity);
        this.isCovered = isCovered;
    }

    public boolean isCovered() { return isCovered; }
}