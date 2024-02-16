package com.learn.springfoundation.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(schema = "TGN", name = "TRAINERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String surname;
    private String firstname;

    @ManyToMany
    @JoinTable(
            name = "T_F",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    private List<FacilityEntity> facilities;

}
