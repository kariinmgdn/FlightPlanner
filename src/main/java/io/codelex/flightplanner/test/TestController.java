package io.codelex.flightplanner.test;

import io.codelex.flightplanner.common.FlightService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestController {
    private final FlightService flightService;

    public TestController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping("/clear")
    public void clear() {
        flightService.clear();
    }
}
