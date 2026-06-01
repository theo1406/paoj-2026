DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS clients;

CREATE TABLE locations (
                           id VARCHAR(50) PRIMARY KEY,
                           name VARCHAR(100),
                           capacity INT,
                           type VARCHAR(20),
                           has_ac BOOLEAN,
                           is_covered BOOLEAN
);

CREATE TABLE clients (
                         id VARCHAR(50) PRIMARY KEY,
                         name VARCHAR(100),
                         email VARCHAR(100)
);

CREATE TABLE events (
                        id VARCHAR(50) PRIMARY KEY,
                        name VARCHAR(100),
                        location_id VARCHAR(50),
                        event_date DATETIME,
                        available_seats INT,
                        type VARCHAR(20),
                        artist VARCHAR(100),
                        sport_type VARCHAR(50),
                        FOREIGN KEY (location_id) REFERENCES locations(id)
);

CREATE TABLE tickets (
                         id VARCHAR(50) PRIMARY KEY,
                         event_id VARCHAR(50),
                         client_id VARCHAR(50),
                         purchase_date DATETIME,
                         FOREIGN KEY (event_id) REFERENCES events(id),
                         FOREIGN KEY (client_id) REFERENCES clients(id)
);