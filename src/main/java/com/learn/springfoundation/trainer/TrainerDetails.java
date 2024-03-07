package com.learn.springfoundation.trainer;

import com.learn.springfoundation.IdText;

import java.util.List;

record TrainerDetails(
        Long id,
        String surname,
        String firstname,
        List<IdText> facilities,
        List<IdText> skills,
        List<IdText> trophies,
        List<IdText> notes
) {
}