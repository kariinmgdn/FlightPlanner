package io.codelex.flightplanner.common.inDB;


import io.codelex.flightplanner.domain.Airport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "flight-planner", name = "type", havingValue = "in-DB")
public interface AirportRepository extends JpaRepository<Airport, String> {
}
