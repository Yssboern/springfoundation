package com.learn.springfoundation.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        log.info("E->DTO " + entity.getFirstname() + " " + entity.getSurname() + " START");
        log.info("E->DTO " + entity.getFirstname() + " " + entity.getSurname() + " facilities");
        List<Long> facilityIds = entity.getFacilities().stream().map(Facility::getFacid).toList();
        log.info("E->DTO " + entity.getFirstname() + " " + entity.getSurname() + " skills");
        List<Long> skills = entity.getSpecialisations().stream().map(Training::getId).toList();
        log.info("E->DTO " + entity.getFirstname() + " " + entity.getSurname() + " trophies");
        List<Long> trophies = entity.getTrophies().stream().map(Trophy::getId).toList();
        log.info("E->DTO " + entity.getFirstname() + " " + entity.getSurname() + " END");
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

    List<TrainerDTO> getAllGraph() {
//        return trainerRepo.findAll()
//                .stream()
//                .map(this::toDTO)
//                .toList();
        return getAll();
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
        var t = trainerRepo.findAll(of);
        var dtos = t.map(this::toDTO);
        return dtos;
    }
}
