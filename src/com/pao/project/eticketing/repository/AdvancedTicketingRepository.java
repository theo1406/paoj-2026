package com.pao.project.eticketing.repository;

import com.pao.project.eticketing.util.DatabaseConnection;
import java.sql.*;
import java.time.LocalDateTime;

public class AdvancedTicketingRepository {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public void buyTicketTransaction(String ticketId, String eventId, String clientId) throws SQLException {
        connection.setAutoCommit(false);
        try {
            String sqlInsert = "INSERT INTO tickets (id, event_id, client_id, purchase_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt1 = connection.prepareStatement(sqlInsert)) {
                stmt1.setString(1, ticketId);
                stmt1.setString(2, eventId);
                stmt1.setString(3, clientId);
                stmt1.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                stmt1.executeUpdate();
            }

            String sqlUpdate = "UPDATE events SET available_seats = available_seats - 1 WHERE id = ?";
            try (PreparedStatement stmt2 = connection.prepareStatement(sqlUpdate)) {
                stmt2.setString(1, eventId);
                stmt2.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void printClientsTicketCount() {
        String sql = "SELECT c.name, COUNT(t.id) as ticket_count FROM clients c LEFT JOIN tickets t ON c.id = t.client_id GROUP BY c.id";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getString("name") + " are " + rs.getInt("ticket_count") + " bilete.");
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void printAllTicketsDetails() {
        String sql = "SELECT t.id, c.name as client_name, e.name as event_name FROM tickets t JOIN clients c ON t.client_id = c.id JOIN events e ON t.event_id = e.id";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Bilet: " + rs.getString("id") + " | Client: " + rs.getString("client_name") + " | Event: " + rs.getString("event_name"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void printEventsWithLocationCapacity() {
        String sql = "SELECT e.name as event_name, l.name as loc_name, l.capacity FROM events e JOIN locations l ON e.location_id = l.id";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Event: " + rs.getString("event_name") + " | Locatie: " + rs.getString("loc_name") + " | Capacitate maxima: " + rs.getInt("capacity"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}