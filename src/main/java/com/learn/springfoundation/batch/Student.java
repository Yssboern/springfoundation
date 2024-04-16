package com.learn.springfoundation.batch;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Integer points;

}
