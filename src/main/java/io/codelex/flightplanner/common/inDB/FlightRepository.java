package io.codelex.flightplanner.common.inDB;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "flight-planner", name = "type", havingValue = "in-DB")
public interface FlightRepository extends JpaRepository<Flight, Long> {

    boolean existsFlightByFromAndToAndCarrierAndDepartureTimeAndArrivalTime(
            Airport from, Airport to, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime);

    @Query("select f from Flight f " +
            "where f.from.airport = :from " +
            "and f.to.airport = :to " +
            "and f.departureTime >= :startTime " +
            "and f.departureTime < :endTime")
    List<Flight> searchFlights(@Param("from") String from,
                               @Param("to") String to,
                               @Param("startTime") LocalDateTime startTime,
                               @Param("endTime") LocalDateTime endTime);

}
