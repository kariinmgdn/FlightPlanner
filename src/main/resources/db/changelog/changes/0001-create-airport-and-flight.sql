--liquibase formatted sql

--changeset karina:1

create table airports
(
    airport varchar(255) primary key,
    country varchar(255) not null,
    city    varchar(255) not null
);

create table flights
(
    flight_id      serial primary key,
    from_airport   varchar(255) not null,
    to_airport     varchar(255) not null,
    carrier        varchar      not null,
    departure_time timestamp    not null,
    arrival_time   timestamp    not null,
    foreign key (from_airport) references airports (airport),
    foreign key (to_airport) references airports (airport)
);