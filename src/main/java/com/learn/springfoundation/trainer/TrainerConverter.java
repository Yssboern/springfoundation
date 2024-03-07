package com.learn.springfoundation.trainer;

import com.learn.springfoundation.IdText;
import com.learn.springfoundation.facility.Facility;
import com.learn.springfoundation.trophy.Trophy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
class TrainerConverter {

    public Trainer toEntity(TrainerDTO dto) {

        Trainer entity = new Trainer();
        entity.setId(dto.id());
        entity.setSurname(dto.surname());
        entity.setFirstname(dto.firstname());

        List<Facility> facilities = new ArrayList<>();
        if (dto.facilityIds() != null) {
            facilities = dto.facilityIds().stream().map(Facility::new).toList();
        }
        entity.setFacilities(facilities);

        List<Training> skills = new ArrayList<>();
        if (dto.specialisations() != null) {
            skills = dto.specialisations().stream().map(Training::new).toList();
        }
        entity.setSpecialisations(skills);

        List<Trophy> trophies = new ArrayList<>();
        if (dto.trophies() != null) {
            trophies = dto.trophies().stream().map(id -> new Trophy(id, entity)).toList();
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

        List<IdText> notes;
        if (entity.getTrainersNotes() == null) {
            notes = Collections.emptyList();
        } else {
            notes = entity.getTrainersNotes().stream().map(n -> new IdText(n.getId(), n.getNote())).toList();
        }

        return new TrainerDTO(
                entity.getId(),
                entity.getSurname(),
                entity.getFirstname(),
                facilityIds,
                skills,
                trophies,
                notes
        );
    }

    public TrainerDetails toDetails(Trainer entity) {
        return new TrainerDetails(
                entity.getId(),
                entity.getSurname(),
                entity.getFirstname(),
                entity.getFacilities().stream()
                        .map(facility -> new IdText(facility.getFacid(), facility.getName()))
                        .collect(Collectors.toList()),
                entity.getSpecialisations().stream()
                        .map(skill -> new IdText(skill.getId(), skill.getName()))
                        .collect(Collectors.toList()),
                entity.getTrophies().stream()
                        .map(trophy -> new IdText(trophy.getId(), trophy.getName()))
                        .collect(Collectors.toList()),
                entity.getTrainersNotes().stream()
                        .map(note -> new IdText(note.getId(), note.getNote()))
                        .collect(Collectors.toList())
        );
    }

}
