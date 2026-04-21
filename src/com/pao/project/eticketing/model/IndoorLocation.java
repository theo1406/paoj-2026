package com.pao.project.eticketing.model;

public class IndoorLocation extends Location {
    private boolean hasAirConditioning;

    public IndoorLocation(String id, String name, int capacity, boolean hasAirConditioning) {
        super(id, name, capacity);
        this.hasAirConditioning = hasAirConditioning;
    }

    public boolean isHasAirConditioning() { return hasAirConditioning; }
}