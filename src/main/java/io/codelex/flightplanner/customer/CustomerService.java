package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.repository.InMemoryRepository;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.domain.SearchFlightsRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CustomerService {

    private final InMemoryRepository inMemory;

    public CustomerService(InMemoryRepository inMemory) {
        this.inMemory = inMemory;
    }

    public Flight findFlightById(int id) {
        return inMemory.findFlightById(id);
    }

    public PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest) {
        return inMemory.searchFlights(searchFlightsRequest);
    }

    public HashSet<Airport> searchAirports(String input) {
        return inMemory.searchAirports(input);
    }
}
