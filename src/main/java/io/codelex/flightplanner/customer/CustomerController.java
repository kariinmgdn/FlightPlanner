package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.domain.PageResult;
import io.codelex.flightplanner.domain.SearchFlightsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashSet;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService costumerService;

    public CustomerController(CustomerService costumerService) {
        this.costumerService = costumerService;
    }

    @GetMapping("/airports")
    public HashSet<Airport> searchAirports(String search) {
        return costumerService.searchAirports(search);
    }

    @GetMapping("/flights/{id}")
    public Flight findFlightById(@PathVariable int id) {
        return costumerService.findFlightById(id);
    }

    @PostMapping("/flights/search")
    public PageResult<Flight> searchFlights(@RequestBody @Valid SearchFlightsRequest searchFlightsRequest) {

        if (searchFlightsRequest.getFrom().equalsIgnoreCase(searchFlightsRequest.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return costumerService.searchFlights(searchFlightsRequest);
    }
}
