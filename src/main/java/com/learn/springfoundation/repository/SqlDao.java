package com.learn.springfoundation.repository;

import org.springframework.stereotype.Service;

@Service
public class SqlDao {

    private final FacilitiesRepo facilitiesRepo;
    private final AppRepo appRepo;

    public SqlDao(FacilitiesRepo facilitiesRepo, AppRepo appRepo) {
        this.facilitiesRepo = facilitiesRepo;
        this.appRepo = appRepo;
    }

    public String one() {

        for (int i = 0; i < 1000; i++) {
            var app = new TstEntity("no: " + i);
            appRepo.save(app);
        }

        try {
//            var ent = facilitiesRepo.save(f);
//            System.out.println(ent.getFacid());
        } catch (Exception e) {
            System.out.println(e);
        }
        var e = appRepo.findAll();
        e.forEach(System.out::println);
        return "blep";
    }

}
