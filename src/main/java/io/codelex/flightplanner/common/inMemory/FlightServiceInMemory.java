package io.codelex.flightplanner.common.inMemory;

import io.codelex.flightplanner.common.FlightServiceInterface;
import io.codelex.flightplanner.domain.*;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@ConditionalOnProperty(prefix = "flight-planner", name = "type", havingValue = "in-memory")
public class FlightServiceInMemory implements FlightServiceInterface {

    private final InMemoryRepository inMemory;

    public FlightServiceInMemory(InMemoryRepository inMemory) {
        this.inMemory = inMemory;
    }

    public synchronized Flight addFlight(AddFlightRequest flightRequest) {
        return inMemory.addFlight(flightRequest);
    }

    public Flight fetchFlight(long id) {
        return inMemory.fetchFlight(id);
    }

    public synchronized void deleteFlight(long id) {
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
