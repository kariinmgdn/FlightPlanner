package io.codelex.flightplanner.dto;

import io.codelex.flightplanner.domain.Airport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AddFlightRequest {

    @NotNull
    private final Airport from;

    @NotNull
    private final Airport to;

    @NotBlank
    private final String carrier;

    @NotNull
    private final LocalDateTime departureTime;

    @NotNull
    private final LocalDateTime arrivalTime;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AddFlightRequest(Airport from, Airport to, String carrier, String departureTime, String arrivalTime) {
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = LocalDateTime.parse(departureTime, formatter);
        this.arrivalTime = LocalDateTime.parse(arrivalTime, formatter);
    }

    public Airport getFrom() {
        return from;
    }

    public Airport getTo() {
        return to;
    }

    public String getCarrier() {
        return carrier;
    }

    public @NotNull LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public @NotNull LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddFlightRequest that = (AddFlightRequest) o;
        return from.equals(that.from) && to.equals(that.to) && carrier.equals(that.carrier) && departureTime.equals(that.departureTime) && arrivalTime.equals(that.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, carrier, departureTime, arrivalTime);
    }
}
