package com.learn.springfoundation.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class BookingView {

    private Long id;
    private String name;
    private String surname;
    private String facilityName;
    private LocalDateTime starttime;
    private Integer slots;
}
