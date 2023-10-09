package com.learn.epam.springfoundation;

import com.learn.epam.springfoundation.entity.AppEntity;
import com.learn.epam.springfoundation.repository.AppRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("DEV")
class AppRepoTests {

    @Autowired
    AppRepo appRepo;

    @Test
    void repositorySavesEntity() {
        //given
        AppEntity e = new AppEntity("Blep");
        //when
        AppEntity r = appRepo.save(e);
        //then
        assertEquals(e, r);
        assertNotNull(r.getId());
    }

    @Test
    void repositoryLoadsEntity() {
        //given
        AppEntity e = new AppEntity("Blep");
        Long id = appRepo.save(e).getId();
        //when
        var r = appRepo.findById(id);
        //then
        assertTrue(r.isPresent());
        var result = r.get();
        assertEquals(id, result.getId());
        assertEquals("Blep", result.getTextValue());
    }

}