package com.learn.epam.springfoundation.entity;

import jakarta.persistence.*;

//facid;name;membercost;guestcost;initialoutlay;monthlymaintenance
@Entity
@Table(schema = "cd", name = "entities")
public class FacilityEntity {
    private Long facid;
    private String name;
    private Float membercost;
    private Float guestcost;
    private Integer monthlymaintenance;

    public FacilityEntity(String[] columns) {
        facid = Long.valueOf(columns[0]);
        name = columns[1];
        membercost = Float.valueOf(columns[2]);
        guestcost = Float.valueOf(columns[3]);
        monthlymaintenance = Integer.valueOf(columns[4]);
    }

    public FacilityEntity() {
    }

    public void setFacid(Long facid) {
        this.facid = facid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getFacid() {
        return facid;
    }
}
