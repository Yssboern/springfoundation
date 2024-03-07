package com.learn.springfoundation.trainer;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainings")
class TrainingController {

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
        System.out.println("http://localhost:8080/api/trainings?page=" + page);
        Page<TrainingDTO> trainingPage = trainingDAO.getPaginatedTrainings(PageRequest.of(page - 1, size));
        System.out.println("----------------------------------------------");
        return ResponseEntity.ok(trainingPage);
    }

    @PostMapping
    public ResponseEntity<TrainingDTO> createTraining(@RequestBody TrainingDTO trainingDTO) {
        TrainingDTO createdTrainingDTO = trainingDAO.createNewTraining(trainingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainingDTO);
    }

}
