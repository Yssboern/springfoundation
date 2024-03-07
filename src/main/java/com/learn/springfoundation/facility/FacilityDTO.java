package com.learn.springfoundation.facility;

import java.util.List;

public record FacilityDTO(
        Long facid,
        String name,
        Float memberCost,
        Float guestCost,
        Integer initialOutlay,
        Integer monthlyMaintenance,
        List<Long> trainerIds
) {
}