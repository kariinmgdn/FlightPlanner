package io.codelex.flightplanner.common;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;

import java.util.HashSet;

public interface FlightRepositoryInterface {

    Flight addFlight(AddFlightRequest flightRequest);

    Flight fetchFlight(long id);

    PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest);

    void deleteFlight(long id);

    HashSet<Airport> searchAirports(String airport);

    void clear();
}
