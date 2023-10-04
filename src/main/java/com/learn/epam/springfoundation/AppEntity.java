package com.learn.epam.springfoundation;

import jakarta.persistence.*;

@Entity
public class AppEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String textValue;

    public AppEntity(String textValue) {
        this.textValue = textValue;
    }

    public AppEntity() {

    }

    public String getTextValue() {
        return textValue;
    }

    public Long getId() {
        return id;
    }
}
