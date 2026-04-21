package com.pao.project.eticketing.model;

import java.time.LocalDateTime;

public final class Ticket {
    private final String id;
    private final String eventId;
    private final String clientId;
    private final LocalDateTime purchaseDate;

    public Ticket(String id, String eventId, String clientId, LocalDateTime purchaseDate) {
        this.id = id;
        this.eventId = eventId;
        this.clientId = clientId;
        this.purchaseDate = purchaseDate;
    }

    public String getId() { return id; }
    public String getEventId() { return eventId; }
    public String getClientId() { return clientId; }
    public LocalDateTime getPurchaseDate() { return purchaseDate; }

    @Override
    public String toString() {
        return "Ticket[ID=" + id + ", EventID=" + eventId + ", Purchased=" + purchaseDate + "]";
    }
}