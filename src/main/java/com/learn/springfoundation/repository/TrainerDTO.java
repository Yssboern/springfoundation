package com.learn.springfoundation.repository;

import java.util.List;

public class TrainerDTO {
    private Long id;
    private String surname;
    private String firstname;
    private List<Long> facilityIds;

    // Constructors, getters, and setters
    public TrainerDTO(Long id, String surname, String firstname, List<Long> facilityIds) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.facilityIds = facilityIds;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Long> getFacilityIds() {
        return facilityIds;
    }

    public void setFacilityIds(List<Long> facilityIds) {
        this.facilityIds = facilityIds;
    }
}