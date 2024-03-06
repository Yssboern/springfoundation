package com.learn.springfoundation.trainer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TRAINERS_NOTES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainersNote {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NOTE", nullable = false, length = 512)
    private String note;

    @ManyToOne
    private Trainer trainer;

    public TrainersNote(String note, Trainer trainer) {
        this.note = note;
        this.trainer = trainer;
    }

}
