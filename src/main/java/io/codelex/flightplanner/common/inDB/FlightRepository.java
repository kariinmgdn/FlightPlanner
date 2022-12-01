package io.codelex.flightplanner.common.inDB;

import io.codelex.flightplanner.domain.Flight;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "flight-planner", name = "type", havingValue = "in-DB")
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
