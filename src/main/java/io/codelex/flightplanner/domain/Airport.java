package io.codelex.flightplanner.domain;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Airport {

    @NotBlank
    private final String country;

    @NotBlank
    private final String city;

    @NotBlank
    private final String airport;

    public Airport(String country, String city, String airport) {
        this.country = formatInput(country);
        this.city = formatInput(city);
        this.airport = airport.toUpperCase().trim();
    }

    private String formatInput(String input) {
        return Arrays
                .stream(input.split(" "))
                .map(originalInput -> originalInput.substring(0, 1).toUpperCase() + originalInput.substring(1).toLowerCase())
                .collect(Collectors.joining(" ")).trim();
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAirport() {
        return airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport1 = (Airport) o;
        return country.equals(airport1.country) && city.equals(airport1.city) && airport.equals(airport1.airport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, airport);
    }
}
