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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "flight-planner", name = "type", havingValue = "in-DB")
public class InDBService implements FlightServiceInterface {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public InDBService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public Flight addFlight(AddFlightRequest flightRequest) {

        Flight newFlight = new Flight(flightRequest.getFrom(), flightRequest.getTo(),
                flightRequest.getCarrier(), flightRequest.getDepartureTime(), flightRequest.getArrivalTime());


        if (flightRepository.existsFlightByFromAndToAndCarrierAndDepartureTimeAndArrivalTime(
                flightRequest.getFrom(), flightRequest.getTo(), flightRequest.getCarrier(),
                flightRequest.getDepartureTime(), flightRequest.getArrivalTime())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        airportRepository.save(newFlight.getFrom());
        airportRepository.save(newFlight.getTo());

        return flightRepository.save(newFlight);

    }

    @Override
    public Flight fetchFlight(long id) {
        return flightRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteFlight(long id) {
        if(flightRepository.findById(id).isPresent()) {
            flightRepository.deleteById(id);
        }
    }

    @Override
    public PageResult<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest) {

        String date = searchFlightsRequest.getDepartureDate();
        LocalDateTime startTime = LocalDate.parse(date).atStartOfDay();
        LocalDateTime endTime = LocalDate.parse(date).plusDays(1).atStartOfDay();

        List<Flight> flights = flightRepository.searchFlights(searchFlightsRequest.getFrom(), searchFlightsRequest.getTo(), startTime, endTime);

        return new PageResult<>(0, flights.size(), flights);
    }

    @Override
    public HashSet<Airport> searchAirports(String input) {
        return airportRepository.findAirports(input.toLowerCase().trim());
    }

    @Override
    public void clear() {
        flightRepository.deleteAll();
    }
}
