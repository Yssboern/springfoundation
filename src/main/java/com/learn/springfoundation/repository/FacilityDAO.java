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

    public static FacilityDTO toDTO(FacilityEntity entity) {
        List<Long> trainerIds = entity.getTrainers().stream()
                .map(TrainerEntity::getId)
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
        List<FacilityDTO> facilities = new ArrayList<>();
        ff.forEach(facilityEntity -> facilities.add(toDTO(facilityEntity)));
        return facilities;
    }

    public FacilityDTO getById(Long id) {
        Optional<FacilityEntity> optionalFacility = facilitiesRepo.findById(id);
        if (optionalFacility.isPresent()) {
            FacilityEntity facilityEntity = optionalFacility.get();
            return toDTO(facilityEntity);
        } else {
            throw new EntityNotFoundException("Facility with id " + id + " not found");
        }
    }

}
