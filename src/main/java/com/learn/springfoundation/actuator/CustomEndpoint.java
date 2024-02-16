package com.learn.springfoundation.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "custom")
@Component
public class CustomEndpoint {

    @ReadOperation
    public Map<String, LocalDateTime> getCurrentDateTime() {
        Map<String, LocalDateTime> out = new HashMap<>();
        out.put("Custom actuator endpoint read at: ", LocalDateTime.now());
        return out;
    }
}