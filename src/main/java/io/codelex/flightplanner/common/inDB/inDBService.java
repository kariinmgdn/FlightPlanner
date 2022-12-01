package io.codelex.flightplanner.common.inDB;

import io.codelex.flightplanner.common.FlightServiceInterface;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "flight-planner", name = "type", havingValue = "in-DB")
public class inDBService implements FlightServiceInterface {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public inDBService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public Flight addFlight(AddFlightRequest flightRequest) {

        Flight newFlight = new Flight(flightRequest.getFrom(), flightRequest.getTo(),
                flightRequest.getCarrier(), flightRequest.getDepartureTime(), flightRequest.getArrivalTime());

        airportRepository.save(newFlight.getFrom());
        airportRepository.save(newFlight.getTo());

        if (flightRepository.findAll().contains(newFlight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return flightRepository.save(newFlight);

    }

    @Override
    public Flight fetchFlight(long id) {

        Flight flight = flightRepository.findAll().stream()
                .filter(flight1 -> flight1.getId() == id).findAny().orElse(null);

        if (flight == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return flight;
    }

    @Override
    public void deleteFlight(long id) {

        if (flightRepository.findAll().stream().anyMatch(flight -> flight.getId() == id)) {
            flightRepository.deleteById(id);
        }
    }

    @Override
    public PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest) {

        List<Flight> flights = new ArrayList<>();

        for (Flight flight : flightRepository.findAll()) {

            if (flight.getFrom().getAirport().equals(searchFlightsRequest.getFrom())
                    && flight.getTo().getAirport().equals(searchFlightsRequest.getTo())
                    && flight.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    .equals(searchFlightsRequest.getDepartureDate())) {
                flights.add(flight);
            }
        }

        return new PageResult<>(0, flights.size(), flights);
    }

    @Override
    public HashSet<Airport> searchAirports(String input) {

        String formattedInput = input.toLowerCase().trim();

        return airportRepository.findAll().stream().filter(airport -> airport.getAirport().toLowerCase().contains(formattedInput)
                        || airport.getCity().toLowerCase().contains(formattedInput)
                        || airport.getCountry().toLowerCase().contains(formattedInput))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public void clear() {
        flightRepository.deleteAll();
    }
}
