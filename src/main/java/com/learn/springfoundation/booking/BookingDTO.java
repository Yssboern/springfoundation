package com.learn.springfoundation.booking;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
class BookingDTO {
    private Long bookid;
    private Long facid; // Assuming you want to store facility ID as a Long
    private Long memid; // Assuming you want to store member ID as a Long
    private LocalDateTime starttime;
    private Integer slots;
}
