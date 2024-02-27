package com.learn.springfoundation.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {

    private final TrainingDAO trainingDAO;

    @Autowired
    public TrainingController(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDTO> getTrainingById(@PathVariable Long id) {
        try {
            TrainingDTO trainingDTO = trainingDAO.getById(id);
            return ResponseEntity.ok(trainingDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<TrainingDTO>> getPaginatedTrainings(@RequestParam(defaultValue = "1") int page,
                                                                   @RequestParam(defaultValue = "7") int size) {
        Page<TrainingDTO> trainingPage = trainingDAO.getPaginatedTrainings(PageRequest.of(page - 1, size));
        return ResponseEntity.ok(trainingPage);
    }

    @PostMapping
    public ResponseEntity<TrainingDTO> createTraining(@RequestBody TrainingDTO trainingDTO) {
        TrainingDTO createdTrainingDTO = trainingDAO.createNewTraining(trainingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainingDTO);
    }

}