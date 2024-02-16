package com.learn.springfoundation;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("DEV")
class AppRepoTests {
    
//
//    @Autowired
//    AppRepo appRepo;
//
//    @Test
//    void repositorySavesEntity() {
//        //given
//        AppEntity e = new AppEntity("Blep");
//        //when
//        AppEntity r = appRepo.save(e);
//        //then
//        assertEquals(e, r);
//        assertNotNull(r.getId());
//    }
//
//    @Test
//    void repositoryLoadsEntity() {
//        //given
//        AppEntity e = new AppEntity("Blep");
//        Long id = appRepo.save(e).getId();
//        //when
//        var r = appRepo.findById(id);
//        //then
//        assertTrue(r.isPresent());
//        var result = r.get();
//        assertEquals(id, result.getId());
//        assertEquals("Blep", result.getTextValue());
//    }

}