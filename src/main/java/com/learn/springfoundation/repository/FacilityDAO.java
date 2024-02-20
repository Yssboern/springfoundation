package com.learn.springfoundation.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FacilityDAO {

    private final FacilitiesRepo facilitiesRepo;

    public FacilityDAO(FacilitiesRepo facilitiesRepo) {
        this.facilitiesRepo = facilitiesRepo;
    }

    public static FacilityDTO toDTO(Facility entity) {

        List<Long> trainerIds = entity.getTrainers().stream()
                .map(Trainer::getId)
                .collect(Collectors.toList());

        return new FacilityDTO(
                entity.getFacid(),
                entity.getName(),
                entity.getMembercost(),
                entity.getGuestcost(),
                entity.getInitialoutlay(),
                entity.getMonthlymaintenance(),
                trainerIds
        );
    }

    List<FacilityDTO> getAll() {
        var ff = facilitiesRepo.findAll();
        List<FacilityDTO> dtos = new ArrayList<>();
        ff.forEach(entity -> dtos.add(toDTO(entity)));
        return dtos;
    }

    public FacilityDTO getById(Long id) {
        Optional<Facility> optionalFacility = facilitiesRepo.findById(id);
        if (optionalFacility.isPresent()) {
            Facility facility = optionalFacility.get();
            return toDTO(facility);
        } else {
            throw new EntityNotFoundException("Facility with id " + id + " not found");
        }
    }

}
