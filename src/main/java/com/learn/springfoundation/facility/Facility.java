package com.learn.springfoundation.facility;

import com.learn.springfoundation.trainer.Trainer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
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

    public Facility(Long id) {
        setFacid(id);
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
