package com.learn.springfoundation.facility;

import lombok.Data;

import java.util.List;

@Data
public class FacilityDTO {
    private Long facid;
    private String name;
    private Float memberCost;
    private Float guestCost;
    private Integer initialOutlay;
    private Integer monthlyMaintenance;
    private List<Long> trainerIds;

    // Constructor, getters, and setters
    // Constructor
    public FacilityDTO(Long facid, String name, Float memberCost, Float guestCost,
                       Integer initialOutlay, Integer monthlyMaintenance, List<Long> trainerIds) {
        this.facid = facid;
        this.name = name;
        this.memberCost = memberCost;
        this.guestCost = guestCost;
        this.initialOutlay = initialOutlay;
        this.monthlyMaintenance = monthlyMaintenance;
        this.trainerIds = trainerIds;
    }
}