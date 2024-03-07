package com.learn.springfoundation.trainer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TrainerDTO {
    private Long id;
    private String surname;
    private String firstname;
    private List<Long> facilityIds;
    private List<Long> specialisations;
    private List<Long> trophies;
    private List<IdText> notes;

    public TrainerDTO(Long id, String surname, String firstname) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
    }

}