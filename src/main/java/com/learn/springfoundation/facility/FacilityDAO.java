package com.learn.springfoundation.facility;

import com.learn.springfoundation.trainer.Trainer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FacilityDAO {

    private final FacilitiesRepo repo;

    public FacilityDAO(FacilitiesRepo repo) {
        this.repo = repo;
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

    public Page<FacilityDTO> getAllPaginated(PageRequest of) {
        return repo.findAll(of)
                .map(FacilityDAO::toDTO);
    }

    public List<FacilityDTO> getAll() {
        var ff = repo.findAll();
        List<FacilityDTO> dtos = new ArrayList<>();
        ff.forEach(entity -> dtos.add(toDTO(entity)));
        return dtos;
    }

    public FacilityDTO getById(Long id) {
        Optional<Facility> optionalFacility = repo.findById(id);
        if (optionalFacility.isPresent()) {
            Facility facility = optionalFacility.get();
            return toDTO(facility);
        } else {
            throw new EntityNotFoundException("Facility with id " + id + " not found");
        }
    }

}
