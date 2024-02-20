package com.learn.springfoundation.repository;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrainerDTO {
    private Long id;
    private String surname;
    private String firstname;
    private List<Long> facilityIds;
    private List<Long> specialisations;
    private List<Long> trophies;

    // Constructors, getters, and setters
    public TrainerDTO(Long id, String surname, String firstname, List<Long> facilityIds, List<Long> specialisations, List<Long> trophies) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.facilityIds = facilityIds;
        this.specialisations = specialisations;
        this.trophies = trophies;
    }

    public TrainerDTO(Long id, String surname, String firstname) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
    }

}