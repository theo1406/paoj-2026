package com.pao.project.eticketing.service;

import com.pao.project.eticketing.exception.EntityNotFoundException;
import com.pao.project.eticketing.exception.EventSoldOutException;
import com.pao.project.eticketing.model.Client;
import com.pao.project.eticketing.model.Event;
import com.pao.project.eticketing.model.Ticket;

import java.time.LocalDateTime;
import java.util.*;

public class ClientService {
    private static final ClientService INSTANCE = new ClientService();

    private final Map<String, Client> clients = new HashMap<>();

    private final Map<String, List<Ticket>> ticketsByClient = new HashMap<>();

    private ClientService() {}

    public static ClientService getInstance() {
        return INSTANCE;
    }

    public void addClient(Client client) {
        if (client != null && client.getId() != null) {
            clients.put(client.getId(), client);
        }
    }

    public void deleteClient(String clientId) throws EntityNotFoundException {
        if (!clients.containsKey(clientId)) {
            throw new EntityNotFoundException("Clientul cu ID " + clientId + " nu exista!");
        }
        clients.remove(clientId);
        ticketsByClient.remove(clientId);
    }

    public Client findClientById(String id) throws EntityNotFoundException {
        Client c = clients.get(id);
        if (c == null) throw new EntityNotFoundException("Clientul nu a fost gasit.");
        return c;
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }

    public void buyTicket(String clientId, String eventId) throws EntityNotFoundException, EventSoldOutException {
        Client client = findClientById(clientId);
        Event event = EventService.getInstance().findEventById(eventId);

        if (event.getAvailableSeats() <= 0) {
            throw new EventSoldOutException("Ne pare rau, nu mai sunt locuri la: " + event.getName());
        }

        event.setAvailableSeats(event.getAvailableSeats() - 1);

        Ticket ticket = new Ticket(UUID.randomUUID().toString(), event.getId(), client.getId(), LocalDateTime.now());

        ticketsByClient.putIfAbsent(clientId, new ArrayList<>());
        ticketsByClient.get(clientId).add(ticket);
    }

    public List<Ticket> getTicketsForClient(String clientId) {
        return ticketsByClient.getOrDefault(clientId, new ArrayList<>());
    }
}