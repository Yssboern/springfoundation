package com.learn.springfoundation.facility;

import com.learn.springfoundation.trainer.Trainer;
import jakarta.persistence.*;

import java.util.List;

//facid;name;membercost;guestcost;initialoutlay;monthlymaintenance
@Entity
@Table(schema = "tgn", name = "facilities")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "facid", nullable = false)
    private Long facid;
    private String name;
    private Float membercost;
    private Float guestcost;
    private Integer initialoutlay;
    private Integer monthlymaintenance;

    @ManyToMany
    @JoinTable(
            name = "T_F",
            joinColumns = @JoinColumn(name = "FACILITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRAINER_ID")
    )
    private List<Trainer> trainers;

    public Facility(String[] columns) {
        this.facid = Long.parseLong(columns[0]) + 1;
        this.name = columns[1];
        this.membercost = Float.valueOf(columns[2]);
        this.guestcost = Float.valueOf(columns[3]);
        this.initialoutlay = Integer.valueOf(columns[4]);
        this.monthlymaintenance = Integer.valueOf(columns[5]);
    }

    public Facility() {

    }

    public Facility(Long id) {
        setFacid(id);
    }

    public Long getFacid() {
        return facid;
    }

    public void setFacid(Long facid) {
        this.facid = facid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMembercost() {
        return membercost;
    }

    public void setMembercost(Float membercost) {
        this.membercost = membercost;
    }

    public Float getGuestcost() {
        return guestcost;
    }

    public void setGuestcost(Float guestcost) {
        this.guestcost = guestcost;
    }

    public Integer getInitialoutlay() {
        return initialoutlay;
    }

    public void setInitialoutlay(Integer initialoutlay) {
        this.initialoutlay = initialoutlay;
    }

    public Integer getMonthlymaintenance() {
        return monthlymaintenance;
    }

    public void setMonthlymaintenance(Integer monthlymaintenance) {
        this.monthlymaintenance = monthlymaintenance;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    @Override
    public String toString() {
        return "FacilityEntity{" +
                "facid=" + facid +
                ", name='" + name + '\'' +
                ", membercost=" + membercost +
                ", guestcost=" + guestcost +
                ", monthlymaintenance=" + monthlymaintenance +
                '}';
    }

}
