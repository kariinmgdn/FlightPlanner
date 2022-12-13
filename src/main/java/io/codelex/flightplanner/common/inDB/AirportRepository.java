package io.codelex.flightplanner.common.inDB;

import io.codelex.flightplanner.domain.Airport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
@ConditionalOnProperty(prefix = "flight-planner", name = "type", havingValue = "in-DB")
public interface AirportRepository extends JpaRepository<Airport, String> {

    @Query("select a from Airport a where lower(a.airport) like :input% or lower(a.city) like :input% or lower(a.country) like :input%")
    HashSet<Airport> findAirports(@Param("input") String input);

}
