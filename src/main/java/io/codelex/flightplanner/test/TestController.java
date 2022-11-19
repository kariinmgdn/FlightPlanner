package io.codelex.flightplanner.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }


    @RequestMapping("/clear")
    public void clear() {
        testService.clear();
    }
}
