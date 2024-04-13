package com.learn.springfoundation.trophy;

import com.learn.springfoundation.trainer.Training;
import com.learn.springfoundation.trainer.Trainer;
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
    private String name;
    private Integer year;
    @ManyToOne
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "DISCIPLINE_ID")
    private Training discipline;

    public Trophy(Long id, Trainer entity) {
        this.id = id;
        this.trainer = entity;
    }

}