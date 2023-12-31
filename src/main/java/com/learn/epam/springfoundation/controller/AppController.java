package com.learn.epam.springfoundation.controller;

import com.learn.epam.springfoundation.entity.AppEntity;
import com.learn.epam.springfoundation.service.AppService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/ping")
    String ping() {
        return "pong!";
    }

    @GetMapping("/dsi")
    String dataSourceInfo() {
        return appService.getDataSourceInfo();
    }

    @GetMapping("/all")
    List<AppEntity> getAll() {
        return appService.getEntities();
    }

    @PostMapping("/populate")
    void populate(@RequestParam(name = "count", defaultValue = "11") int count) {
        appService.populateDB(count);
    }

}
