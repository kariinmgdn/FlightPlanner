package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.common.FlightServiceInterface;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api/flights")
public class AdminController {

    private final FlightServiceInterface flightsService;

    public AdminController(FlightServiceInterface flightsService) {
        this.flightsService = flightsService;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@RequestBody @Valid AddFlightRequest flightRequest) {

        if (flightRequest.getDepartureTime().isAfter(flightRequest.getArrivalTime())
                || flightRequest.getDepartureTime().equals(flightRequest.getArrivalTime())
                || flightRequest.getFrom().getAirport().trim()
                .equalsIgnoreCase(flightRequest.getTo().getAirport().trim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return flightsService.addFlight(flightRequest);
    }

    @GetMapping("{id}")
    public Flight fetchFlight(@PathVariable long id) {
        return flightsService.fetchFlight(id);
    }

    @DeleteMapping("{id}")
    public synchronized void deleteFlight(@PathVariable long id) {
        flightsService.deleteFlight(id);
    }
}
