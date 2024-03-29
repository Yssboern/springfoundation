package com.learn.springfoundation.trainer;

import com.learn.springfoundation.facility.Facility;
import com.learn.springfoundation.trophy.Trophy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(schema = "TGN", name = "TRAINERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    private String surname;
    private String firstname;

    @BatchSize(size = 10)
    @ManyToMany
    @JoinTable(name = "TRAINER_SKILLS",
            joinColumns = @JoinColumn(name = "TRAINER_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRAINING_ID"))
    @Fetch(FetchMode.SUBSELECT)
    private List<Training> specialisations;

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "trainer")
    @Fetch(FetchMode.SUBSELECT)
    private List<Trophy> trophies;

    @BatchSize(size = 10)
    @ManyToMany
    @JoinTable(
            name = "T_F",
            joinColumns = @JoinColumn(name = "TRAINER_ID"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    @Fetch(FetchMode.SUBSELECT)
    private List<Facility> facilities;

    @OneToOne
    private PersonalID personalID;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TrainersNote> trainersNotes;

    public Trainer(Long trainerId) {
        this.id = trainerId;
    }
}
