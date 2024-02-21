package com.learn.springfoundation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainerController {

    @Autowired
    private TrainerDAO trainerDAO;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/trainers")
    public Page<TrainerDTO> getTrainers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return trainerDAO.getPaginatedTrainers(PageRequest.of(page-1, size));
    }

}
