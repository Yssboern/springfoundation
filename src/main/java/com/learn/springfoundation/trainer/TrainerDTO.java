package com.learn.springfoundation.trainer;

import com.learn.springfoundation.IdText;

import java.util.List;

record TrainerDTO(
        Long id,
        String surname,
        String firstname,
        List<Long> facilityIds,
        List<Long> specialisations,
        List<Long> trophies,
        List<IdText> notes
) {
}