package io.codelex.flightplanner.common;

import io.codelex.flightplanner.domain.*;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class FlightService {

    private final InMemoryRepository inMemory;

    public FlightService(InMemoryRepository inMemory) {
        this.inMemory = inMemory;
    }

    public synchronized Flight addFlight(AddFlightRequest flightRequest) {
        return inMemory.addFlight(flightRequest);
    }

    public Flight fetchFlight(int id) {
        return inMemory.fetchFlight(id);
    }

    public synchronized void deleteFlight(int id) {
        inMemory.deleteFlight(id);
    }


    public PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest) {
        return inMemory.searchFlights(searchFlightsRequest);
    }

    public HashSet<Airport> searchAirports(String input) {
        return inMemory.searchAirports(input);
    }


    public void clear() {
        inMemory.clear();
    }
}
