CREATE TYPE AUTO_CLASS AS ENUM ('A', 'B', 'C', 'D');

CREATE TYPE RIDE_STATUS AS ENUM ('WAITING', 'COMPLETED', 'REJECTED');

CREATE TYPE USER_ROLE AS ENUM ('DRIVER', 'ADMIN', 'USER');

CREATE TABLE Automobiles
(
    id                   SERIAL PRIMARY KEY,
    seats                INTEGER DEFAULT 4,
    last_inspection_date DATE       NOT NULL,
    class                AUTO_CLASS NOT NULL
);

CREATE TABLE Users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(32) UNIQUE NOT NULL,
    password VARCHAR(32) NOT NULL,
    car_id   INTEGER REFERENCES Automobiles (id),
    name     VARCHAR(50) NOT NULL,
    surname  VARCHAR(50) NOT NULL,
    role     USER_ROLE DEFAULT 'DRIVER'
);

CREATE TABLE Bookings
(
    id          SERIAL PRIMARY KEY,
    min_class   AUTO_CLASS  DEFAULT 'D',
    depart      VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    min_seats   INTEGER     DEFAULT 1,
    status      RIDE_STATUS DEFAULT 'WAITING'
);


CREATE TABLE Rides
(
    id         SERIAL PRIMARY KEY,
    car_id     INTEGER REFERENCES Automobiles (id),
    booking_id INTEGER REFERENCES Bookings (id),
    cost       INTEGER NOT NULL
);