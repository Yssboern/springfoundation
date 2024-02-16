package com.learn.springfoundation.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TrainerDAO {

    private final TrainerRepo trainerRepo;

    public TrainerDAO(TrainerRepo trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    public TrainerDTO toDTO(TrainerEntity entity) {
        List<Long> facilityIds = entity.getFacilities().stream()
                .map(FacilityEntity::getFacid)
                .collect(Collectors.toList());

        return new TrainerDTO(
                entity.getId(),
                entity.getSurname(),
                entity.getFirstname(),
                facilityIds
        );
    }

    List<TrainerDTO> getAll() {
        var ff = trainerRepo.findAll();
        List<TrainerDTO> facilities = new ArrayList<>();
        ff.forEach(facilityEntity -> facilities.add(toDTO(facilityEntity)));
        return facilities;
    }

    List<TrainerDTO> getAllGraph() {
//        return trainerRepo.findAll()
//                .stream()
//                .map(this::toDTO)
//                .toList();
        return getAll();
    }

    public TrainerDTO getById(Long id) {
        Optional<TrainerEntity> optionalTrainer = trainerRepo.findById(id);
        if (optionalTrainer.isPresent()) {
            TrainerEntity trainerEntity = optionalTrainer.get();
            return toDTO(trainerEntity);
        } else {
            throw new EntityNotFoundException("Trainer with id " + id + " not found");
        }
    }

}
