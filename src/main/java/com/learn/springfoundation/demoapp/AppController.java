package com.learn.springfoundation.demoapp;

import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api")
class AppController {

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
    List<TstEntity> getAll() {
        return appService.getEntities();
    }

    @PostMapping("/populate")
    void populate(@RequestParam(name = "count", defaultValue = "11") int count) {
        appService.populateDB(count);
    }

    @GetMapping("/marker")
    public void markConsole() {
        System.out.println("########################################################");
        System.out.println("Current Time: " + LocalTime.now().toString());
        System.out.println("########################################################");
    }

}
