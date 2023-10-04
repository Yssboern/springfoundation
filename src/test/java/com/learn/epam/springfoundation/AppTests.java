package com.learn.epam.springfoundation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppTests {

    @Autowired
    AppRepo appRepo;

    @Autowired
    AppService appService;

    @Test
    void contextLoads() {

        appService.populateDB(10);
        AppEntity e = new AppEntity("Blep");
        AppEntity r = appRepo.save(e);
        assertEquals(e, r);
    }

}
