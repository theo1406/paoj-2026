package com.pao.project.eticketing;

import com.pao.project.eticketing.exception.EntityNotFoundException;
import com.pao.project.eticketing.exception.EventSoldOutException;
import com.pao.project.eticketing.model.*;
import com.pao.project.eticketing.service.ClientService;
import com.pao.project.eticketing.service.EventService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    private static final EventService eventService = EventService.getInstance();
    private static final ClientService clientService = ClientService.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initData();

        boolean running = true;
        while (running) {
            afiseazaMeniu();
            System.out.print("Alege o optiune: ");
            String optiune = scanner.nextLine().trim();

            try {
                switch (optiune) {
                    case "1" -> adaugaLocatie();
                    case "2" -> adaugaEveniment();
                    case "3" -> adaugaClient();
                    case "4" -> afiseazaLocatii();
                    case "5" -> afiseazaClienti();
                    case "6" -> afiseazaEvenimenteSortate();
                    case "7" -> cautaEveniment();
                    case "8" -> cumparaBilet();
                    case "9" -> afiseazaIstoricBilete();
                    case "10" -> stergeClient();
                    case "0" -> {
                        System.out.println("Iesire din aplicatie. La revedere!");
                        running = false;
                    }
                    default -> System.out.println("Optiune invalida! Te rog sa incerci din nou.");
                }
            } catch (Exception e) {
                System.out.println(">> EROARE: " + e.getMessage());
            }
        }
    }

    private static void afiseazaMeniu() {
        System.out.println("\n=========================================");
        System.out.println("        PLATFORMA E-TICKETING");
        System.out.println("=========================================");
        System.out.println("1. Adauga o locatie noua");
        System.out.println("2. Adauga un eveniment nou");
        System.out.println("3. Inregistreaza un client nou");
        System.out.println("4. Afiseaza toate locatiile");
        System.out.println("5. Afiseaza toti clientii");
        System.out.println("6. Afiseaza toate evenimentele (sortate cronologic)");
        System.out.println("7. Cauta un eveniment dupa nume");
        System.out.println("8. Cumpara un bilet");
        System.out.println("9. Afiseaza istoricul biletelor unui client");
        System.out.println("10. Sterge un client din sistem");
        System.out.println("0. Iesire");
        System.out.println("=========================================");
    }

    private static void adaugaLocatie() {
        System.out.print("ID Locatie: ");
        String id = scanner.nextLine();
        System.out.print("Nume Locatie: ");
        String nume = scanner.nextLine();
        System.out.print("Capacitate: ");
        int capacitate = Integer.parseInt(scanner.nextLine());
        System.out.print("Tip locatie (1 - Indoor, 2 - Outdoor): ");
        String tip = scanner.nextLine();

        if (tip.equals("1")) {
            System.out.print("Are aer conditionat? (true/false): ");
            boolean areAc = Boolean.parseBoolean(scanner.nextLine());
            eventService.addLocation(new IndoorLocation(id, nume, capacitate, areAc));
        } else {
            System.out.print("Este acoperita? (true/false): ");
            boolean esteAcoperita = Boolean.parseBoolean(scanner.nextLine());
            eventService.addLocation(new OutdoorLocation(id, nume, capacitate, esteAcoperita));
        }
        System.out.println("Locatie adaugata cu succes!");
    }

    private static void adaugaEveniment() throws EntityNotFoundException {
        System.out.print("ID Eveniment: ");
        String id = scanner.nextLine();
        System.out.print("Nume Eveniment: ");
        String nume = scanner.nextLine();
        System.out.print("ID Locatie unde va avea loc: ");
        String idLocatie = scanner.nextLine();

        Location locatie = eventService.getAllLocations().stream()
                .filter(l -> l.getId().equals(idLocatie))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Locatia cu ID-ul specificat nu exista!"));

        System.out.print("Anul: "); int an = Integer.parseInt(scanner.nextLine());
        System.out.print("Luna: "); int luna = Integer.parseInt(scanner.nextLine());
        System.out.print("Ziua: "); int zi = Integer.parseInt(scanner.nextLine());
        System.out.print("Ora: "); int ora = Integer.parseInt(scanner.nextLine());
        LocalDateTime data = LocalDateTime.of(an, luna, zi, ora, 0);

        System.out.print("Tip eveniment (1 - Concert, 2 - Meci Sportiv): ");
        String tip = scanner.nextLine();

        if (tip.equals("1")) {
            System.out.print("Nume artist: ");
            String artist = scanner.nextLine();
            eventService.addEvent(new Concert(id, nume, locatie, data, artist));
        } else {
            System.out.print("Tip sport (ex: Fotbal, Tenis): ");
            String sport = scanner.nextLine();
            eventService.addEvent(new SportsMatch(id, nume, locatie, data, sport));
        }
        System.out.println("Eveniment adaugat cu succes!");
    }

    private static void adaugaClient() {
        System.out.print("ID Client: ");
        String id = scanner.nextLine();
        System.out.print("Nume Client: ");
        String nume = scanner.nextLine();
        System.out.print("Email Client: ");
        String email = scanner.nextLine();

        clientService.addClient(new Client(id, nume, email));
        System.out.println("Client adaugat cu succes!");
    }

    private static void afiseazaLocatii() {
        System.out.println("\n--- Lista Locatii ---");
        eventService.getAllLocations().forEach(System.out::println);
    }

    private static void afiseazaClienti() {
        System.out.println("\n--- Lista Clienti ---");
        clientService.getAllClients().forEach(System.out::println);
    }

    private static void afiseazaEvenimenteSortate() {
        System.out.println("\n--- Program Evenimente (Sortate Cronologic) ---");
        eventService.getAllEvents().forEach(System.out::println);
    }

    private static void cautaEveniment() throws EntityNotFoundException {
        System.out.print("Introdu numele evenimentului cautat: ");
        String nume = scanner.nextLine();
        Event event = eventService.findEventByName(nume);
        System.out.println("Eveniment gasit: " + event);
    }

    private static void cumparaBilet() throws EntityNotFoundException, EventSoldOutException {
        System.out.print("ID Client care cumpara: ");
        String idClient = scanner.nextLine();
        System.out.print("ID Eveniment dorit: ");
        String idEveniment = scanner.nextLine();

        clientService.buyTicket(idClient, idEveniment);
        System.out.println("Bilet achizitionat cu succes!");
    }

    private static void afiseazaIstoricBilete() {
        System.out.print("ID Client pentru istoric: ");
        String idClient = scanner.nextLine();
        System.out.println("\n--- Bilete pentru clientul " + idClient + " ---");

        var bilete = clientService.getTicketsForClient(idClient);
        if (bilete.isEmpty()) {
            System.out.println("Acest client nu are niciun bilet cumparat.");
        } else {
            bilete.forEach(System.out::println);
        }
    }

    private static void stergeClient() throws EntityNotFoundException {
        System.out.print("ID Client de sters: ");
        String idClient = scanner.nextLine();
        clientService.deleteClient(idClient);
        System.out.println("Clientul a fost sters din sistem.");
    }

    private static void initData() {
        Location l1 = new IndoorLocation("L1", "Sala Palatului", 4000, true);
        Location l2 = new OutdoorLocation("L2", "Arena Nationala", 50000, false);
        eventService.addLocation(l1);
        eventService.addLocation(l2);

        Event e1 = new Concert("E1", "Concert Craciun", l1, LocalDateTime.of(2026, 12, 20, 20, 0), "Stefan Banica");
        Event e2 = new SportsMatch("E2", "Derby Fotbal", l2, LocalDateTime.of(2026, 8, 15, 21, 30), "Fotbal");
        eventService.addEvent(e1);
        eventService.addEvent(e2);

        Client c1 = new Client("C1", "Mihai Popescu", "mihai@mail.com");
        Client c2 = new Client("C2", "Ana Enache", "ana@mail.com");
        clientService.addClient(c1);
        clientService.addClient(c2);
    }


}