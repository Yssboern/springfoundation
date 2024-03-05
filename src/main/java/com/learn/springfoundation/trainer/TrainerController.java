package com.learn.springfoundation.trainer;

import com.learn.springfoundation.repository.NewTrainer;
import com.learn.springfoundation.repository.TrainerDAO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TrainerController {

    private final TrainerDAO trainerDAO;
    private final TrainerDetailsDAO trainerDetailsDAO;

    @GetMapping("/trainers")
    public Page<TrainerDTO> getTrainers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return trainerDAO.getPaginatedTrainers(PageRequest.of(page - 1, size));
    }

    @GetMapping("/trainers/{id}")
    public ResponseEntity<TrainerDetails> getTrainers(@PathVariable Long id) {
        System.out.println("http://localhost:8080/api/trainers/" + id);
        var out = trainerDetailsDAO.getById(id);
        System.out.println("----------------------------------");
        return ResponseEntity.status(HttpStatus.OK).body(out);
    }

    @PostMapping("/trainers")
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody NewTrainer trainer) {
        var createdTrainer = trainerDAO.save(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainer);
    }

    @PutMapping("/trainers/{id}")
    public ResponseEntity<TrainerDTO> updateTrainer(@RequestBody TrainerDTO trainer) {
        var updatedTrainer = trainerDAO.update(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedTrainer);
    }

    @GetMapping("/trainers/{id}/skills")

    public List<IdName> getTrainerSkills(@PathVariable Long id) {
        return trainerDetailsDAO.getTrainingDisplayList(id);
    }

    @GetMapping("/trainers/{id}/notes")
    public List<IdName> getTrainerNotes(@PathVariable Long id) {
        return trainerDetailsDAO.getTrainersNotes(id);
    }

    @PostMapping("/trainers/{id}/notes")
    public TrainerDetails addTrainerNote(@PathVariable Long id, @RequestBody IdName note) {
        return trainerDetailsDAO.addTrainersNote2(id, note);
    }

}
