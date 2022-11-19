package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.repository.InMemoryRepository;
import io.codelex.flightplanner.domain.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final InMemoryRepository inMemory;

    public AdminService(InMemoryRepository inMemory) {
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
}
