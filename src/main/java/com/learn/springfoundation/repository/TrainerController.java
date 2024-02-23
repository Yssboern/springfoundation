package com.learn.springfoundation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TrainerController {

    @Autowired
    private TrainerDAO trainerDAO;

    @GetMapping("/trainers")
    public Page<TrainerDTO> getTrainers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return trainerDAO.getPaginatedTrainers(PageRequest.of(page - 1, size));
    }

    @GetMapping("/trainers/{id}")
    public TrainerDTO getTrainers(@PathVariable Long id) {
        return trainerDAO.getById(id);
    }

    @PostMapping("/trainers")
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody NewTrainer trainer) {
        var createdTrainer = trainerDAO.createNewTrainer(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainer);
    }
}
