package com.learn.springfoundation.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "TGN", name = "TROPHIES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trophy {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    private String name;
    private Integer year;

    @ManyToOne
    private Training discipline;

}