package io.codelex.flightplanner.domain;

import java.util.HashSet;

@org.springframework.stereotype.Repository
public interface FlightRepository {

    Flight addFlight(AddFlightRequest flightRequest);

    Flight fetchFlight(int id);

    Flight findFlightById(int id);

    PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest);

    void deleteFlight(int id);

    HashSet<Airport> searchAirports(String airport);
    void clear();
}
