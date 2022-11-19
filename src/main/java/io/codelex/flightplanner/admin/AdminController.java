package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.domain.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api/flights")
public class AdminController {

    private final AdminService flightsService;

    public AdminController(AdminService flightsService) {
        this.flightsService = flightsService;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@RequestBody @Valid AddFlightRequest flightRequest) {

        if (flightRequest.getDepartureTime().isAfter(flightRequest.getArrivalTime())
                || flightRequest.getDepartureTime().equals(flightRequest.getArrivalTime())
                || flightRequest.getFrom().equals(flightRequest.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return flightsService.addFlight(flightRequest);
    }

    @GetMapping("{id}")
    public Flight fetchFlight(@PathVariable int id) {
        return flightsService.fetchFlight(id);
    }

    @DeleteMapping("{id}")
    public synchronized void deleteFlight(@PathVariable int id) {
        flightsService.deleteFlight(id);
    }
}
