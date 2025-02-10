package cz.upce.fei.nnpiacv.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String sayGreeting() {
        return "<h1>Hello World again!</h1>";
    }
}
