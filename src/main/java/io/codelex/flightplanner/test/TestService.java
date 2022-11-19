package io.codelex.flightplanner.test;

import io.codelex.flightplanner.repository.InMemoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final InMemoryRepository inMemory;

    public TestService(InMemoryRepository inMemory) {
        this.inMemory = inMemory;
    }

    public void clear() {
        inMemory.clear();
    }
}
