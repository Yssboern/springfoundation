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
//@NamedEntityGraph(name = "TrainerEntity.facilities",  attributeNodes = @NamedAttributeNode("facilities"))
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    private String surname;
    private String firstname;

    @ManyToMany
//    @Fetch(FetchMode.SUBSELECT)
    private List<Training> specialisations;

    @OneToMany(mappedBy = "trainer")
//    @Fetch(FetchMode.SUBSELECT)
    private List<Trophy> trophies = new java.util.ArrayList<>();

    //    @ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany
    @JoinTable(
            name = "T_F",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
//    @Fetch(FetchMode.SUBSELECT)
    private List<Facility> facilities;

}
