package io.codelex.flightplanner.common;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;

import java.util.HashSet;

public interface FlightServiceInterface {

    Flight addFlight(AddFlightRequest flightRequest);

    Flight fetchFlight(long id);

    void deleteFlight(long id);

    PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest);

    HashSet<Airport> searchAirports(String input);

    void clear();

}
