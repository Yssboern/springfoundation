package com.learn.springfoundation.trainer;


import com.learn.springfoundation.trainer.Trainer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(schema = "TGN", name = "TRAININGS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Training {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "TRAINER_SKILLS",
            joinColumns = @JoinColumn(name = "TRAINING_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRAINER_ID")
    )
    private List<Trainer> trainers;

    public Training(Long id) {
        this.id = id;
    }
}
