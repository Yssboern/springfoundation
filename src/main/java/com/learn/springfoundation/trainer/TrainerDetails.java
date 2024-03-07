package com.learn.springfoundation.trainer;

import java.util.List;

record TrainerDetails(
        Long id,
        String surname,
        String firstname,
        List<IdName> facilities,
        List<IdName> skills,
        List<IdName> trophies,
        List<IdName> notes
) {
}