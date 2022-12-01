package io.codelex.flightplanner.test;

import io.codelex.flightplanner.common.FlightServiceInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestController {
    private final FlightServiceInterface flightService;

    public TestController(FlightServiceInterface flightService) {
        this.flightService = flightService;
    }

    @RequestMapping("/clear")
    public void clear() {
        flightService.clear();
    }
}
