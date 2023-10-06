package com.learn.epam.springfoundation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("DEV")
class AppTests {

    @Autowired
    AppRepo appRepo;

    @Test
    void contextLoads() {
        AppEntity e = new AppEntity("Blep");
        AppEntity r = appRepo.save(e);
        assertEquals(e, r);
    }

}