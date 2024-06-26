package com.learn.springfoundation.trainer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(schema = "TGN", name = "PERSONALIDS")
@Getter
@Setter
@AllArgsConstructor
class PersonalID {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    Long id;
    UUID uuid;

    @OneToOne
    Trainer trainer;

    public PersonalID() {
        uuid = UUID.randomUUID();
    }

    public PersonalID(Long id, Trainer trainer) {
        this.id = id;
        this.trainer = trainer;
    }
}
