package com.learn.springfoundation.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class TrainingDAO {

    private final TrainingRepo trainingRepo;

    @Autowired
    public TrainingDAO(TrainingRepo trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    public TrainingDTO toDTO(Training entity) {
        return new TrainingDTO(entity.getId(), entity.getName());
    }

    public TrainingDTO getById(Long id) {
        Optional<Training> optionalTraining = trainingRepo.findById(id);
        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get();
            return toDTO(training);
        } else {
            throw new EntityNotFoundException("Training with id " + id + " not found");
        }
    }

    public Page<TrainingDTO> getPaginatedTrainings(PageRequest pageRequest) {
        return trainingRepo.findAll(pageRequest)
                .map(this::toDTO);
    }

    public TrainingDTO createNewTraining(TrainingDTO trainingDTO) {
        Training newEntity = new Training();
        newEntity.setName(trainingDTO.getName());
        Training savedTraining = trainingRepo.save(newEntity);
        log.info("New training created: " + savedTraining.getName());
        return toDTO(savedTraining);
    }

//    public List<TrainingDTO> getAll() {
//        log.info("Fetching all Trainings: START");
//        List<Training> entities = trainingRepo.findAll();
//        List<TrainingDTO> dtos = new ArrayList<>();
//        entities.forEach(trainingEntity -> dtos.add(toDTO(trainingEntity)));
//        log.info("Fetching all Trainings: STOP");
//        return dtos;
//    }
}
