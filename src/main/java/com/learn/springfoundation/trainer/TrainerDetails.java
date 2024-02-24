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
public class TrainerDetails {

    private Long id;
    private String surname;
    private String firstname;
    private List<IdName> facilities;
    private List<IdName> skills;
    private List<IdName> trophies;


}
