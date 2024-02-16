package com.learn.springfoundation.repository;

import jakarta.persistence.*;

@Entity
@Table(schema = "TGN", name = "TSTTABLE")
public class TstEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String textValue;

    public TstEntity(String textValue) {
        this.textValue = textValue;
    }

    public TstEntity() {

    }

    public String getTextValue() {
        return textValue;
    }

    public Long getId() {
        return id;
    }
}
