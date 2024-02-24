package com.learn.springfoundation.repository;

import com.learn.springfoundation.trainer.Trainer;
import com.learn.springfoundation.trainer.TrainerDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class TrainerDAO {

    private final TrainerRepo trainerRepo;
    private final TrainerConverter converter;

    List<TrainerDTO> getAll() {
        log.info("Fetching all Trainers: START");
        var entities = trainerRepo.findAll();
        List<TrainerDTO> dtos = new ArrayList<>();
        entities.forEach(facilityEntity -> dtos.add(converter.toDTO(facilityEntity)));
        log.info("Fetching all Trainers: STOP");
        return dtos;
    }

    public TrainerDTO getById(Long id) {
        Optional<Trainer> optionalTrainer = trainerRepo.findById(id);
        if (optionalTrainer.isPresent()) {
            Trainer trainer = optionalTrainer.get();
            return converter.toDTO(trainer);
        } else {
            throw new EntityNotFoundException("Trainer with id " + id + " not found");
        }
    }

    public Page<TrainerDTO> getPaginatedTrainers(PageRequest of) {
        return trainerRepo.findAll(of)
                .map(converter::toDTO);
    }

    public TrainerDTO save(NewTrainer trainer) {
        var newEntity = new Trainer();
        newEntity.setFirstname(trainer.getFirstName());
        newEntity.setSurname(trainer.getLastName());
        newEntity.setSpecialisations(Collections.emptyList());
        newEntity.setFacilities(Collections.emptyList());
        newEntity.setTrophies(Collections.emptyList());
        var e = trainerRepo.save(newEntity);
        log.info("New trainer created: " + e.getFirstname() + " " + e.getSurname());
        return converter.toDTO(e);
    }

    public TrainerDTO update(TrainerDTO trainer) {
        var entity = converter.toEntity(trainer);
        var e = trainerRepo.save(entity);
        log.info("New trainer created: " + e.getFirstname() + " " + e.getSurname());
        return converter.toDTO(e);
    }

}
