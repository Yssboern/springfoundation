package com.learn.springfoundation.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class TrainerDAO {

    private final TrainerRepo trainerRepo;

    public TrainerDAO(TrainerRepo trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    public TrainerDTO toDTO(Trainer entity) {

        List<Long> facilityIds;
        if (entity.getFacilities() == null) {
            facilityIds = entity.getFacilities().stream().map(Facility::getFacid).toList();
        } else {
            facilityIds = Collections.emptyList();
        }

        List<Long> skills;
        if (entity.getSpecialisations() == null) {
            skills = entity.getSpecialisations().stream().map(Training::getId).toList();
        } else {
            skills = Collections.emptyList();
        }

        List<Long> trophies;
        if (entity.getSpecialisations() == null) {
            trophies = entity.getTrophies().stream().map(Trophy::getId).toList();
        } else {
            trophies = Collections.emptyList();
        }

        return new TrainerDTO(
                entity.getId(),
                entity.getSurname(),
                entity.getFirstname(),
                facilityIds,
                skills,
                trophies
        );
    }

    List<TrainerDTO> getAll() {
        log.info("Fetching all Trainers: START");
        var entities = trainerRepo.findAll();
        List<TrainerDTO> dtos = new ArrayList<>();
        entities.forEach(facilityEntity -> dtos.add(toDTO(facilityEntity)));
        log.info("Fetching all Trainers: STOP");
        return dtos;
    }

    public TrainerDTO getById(Long id) {
        Optional<Trainer> optionalTrainer = trainerRepo.findById(id);
        if (optionalTrainer.isPresent()) {
            Trainer trainer = optionalTrainer.get();
            return toDTO(trainer);
        } else {
            throw new EntityNotFoundException("Trainer with id " + id + " not found");
        }
    }

    public Page<TrainerDTO> getPaginatedTrainers(PageRequest of) {
        return trainerRepo.findAll(of)
                .map(this::toDTO);
    }

    public TrainerDTO createNewTrainer(NewTrainer trainer) {
        var newEntity = new Trainer();
        newEntity.setFirstname(trainer.getFirstName());
        newEntity.setSurname(trainer.getLastName());
        newEntity.setSpecialisations(Collections.emptyList());
        newEntity.setFacilities(Collections.emptyList());
        newEntity.setTrophies(Collections.emptyList());
        var e = trainerRepo.save(newEntity);
        log.info("New trainer created: " + e.getFirstname() + " " + e.getSurname());
        return toDTO(e);
    }

}
