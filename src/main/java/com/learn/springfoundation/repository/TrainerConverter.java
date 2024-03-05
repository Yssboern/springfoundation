package com.learn.springfoundation.repository;

import com.learn.springfoundation.trainer.IdName;
import com.learn.springfoundation.trainer.Trainer;
import com.learn.springfoundation.trainer.TrainerDTO;
import com.learn.springfoundation.trainer.TrainerDetails;
import com.learn.springfoundation.trophy.Trophy;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TrainerConverter {

    public Trainer toEntity(TrainerDTO dto) {

        Trainer entity = new Trainer();
        entity.setId(dto.getId());
        entity.setSurname(dto.getSurname());
        entity.setFirstname(dto.getFirstname());

        List<Facility> facilities = new ArrayList<>();
        if (dto.getFacilityIds() != null) {
            facilities = dto.getFacilityIds().stream().map(Facility::new).toList();
        }
        entity.setFacilities(facilities);

        List<Training> skills = new ArrayList<>();
        if (dto.getSpecialisations() != null) {
            skills = dto.getSpecialisations().stream().map(Training::new).toList();
        }
        entity.setSpecialisations(skills);

        List<Trophy> trophies = new ArrayList<>();
        if (dto.getTrophies() != null) {
            trophies = dto.getTrophies().stream().map(id -> new Trophy(id, entity)).toList();
        }
        entity.setTrophies(trophies);
        return entity;
    }

    public TrainerDTO toDTO(Trainer entity) {

        List<Long> facilityIds;
        if (entity.getFacilities() == null) {
            facilityIds = Collections.emptyList();
        } else {
            facilityIds = entity.getFacilities().stream().map(Facility::getFacid).toList();
        }

        List<Long> skills;
        if (entity.getSpecialisations() == null) {
            skills = Collections.emptyList();
        } else {
            skills = entity.getSpecialisations().stream().map(Training::getId).toList();
        }

        List<Long> trophies;
        if (entity.getSpecialisations() == null) {
            trophies = Collections.emptyList();
        } else {
            trophies = entity.getTrophies().stream().map(Trophy::getId).toList();
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

    public TrainerDetails toDetails(Trainer entity){
            TrainerDetails details = new TrainerDetails();
            details.setId(entity.getId());
            details.setFirstname(entity.getFirstname());
            details.setSurname(entity.getSurname());
            details.setFacilities(entity.getFacilities().stream().map(facility -> new IdName(facility.getFacid(), facility.getName())).toList());
            details.setSkills(entity.getSpecialisations().stream().map(skill -> new IdName(skill.getId(), skill.getName())).toList());
            details.setTrophies(entity.getTrophies().stream().map(trophy -> new IdName(trophy.getId(), trophy.getName())).toList());
            details.setTrophies(entity.getTrophies().stream().map(trophy -> new IdName(trophy.getId(), trophy.getName())).toList());
            details.setNotes(entity.getTrainersNotes().stream().map(trophy -> new IdName(trophy.getId(), trophy.getNote())).toList());
            return details;
    }
}
