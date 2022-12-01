package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.common.FlightServiceInterface;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashSet;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final FlightServiceInterface flightService;

    public CustomerController(FlightServiceInterface flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/airports")
    public HashSet<Airport> searchAirports(String search) {
        return flightService.searchAirports(search);
    }

    @GetMapping("/flights/{id}")
    public Flight findFlightById(@PathVariable long id) {
        return flightService.fetchFlight(id);
    }

    @PostMapping("/flights/search")
    public PageResult<Flight> searchFlights(@RequestBody @Valid SearchFlightsRequest searchFlightsRequest) {

        if (searchFlightsRequest.getFrom().equalsIgnoreCase(searchFlightsRequest.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return flightService.searchFlights(searchFlightsRequest);
    }
}
