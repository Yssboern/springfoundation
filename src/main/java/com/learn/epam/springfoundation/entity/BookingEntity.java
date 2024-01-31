package com.learn.epam.springfoundation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//bookid;facid;memid;starttime;slots
@Entity
@Table(schema = "cd", name = "bookings")
public class BookingEntity {
    private Long bookid;
    private Long facid;
    private Long memid;
    private LocalDateTime starttime;
    private Integer slots;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public BookingEntity(String[] params) {
        bookid = Long.valueOf(params[0]);
        facid = Long.valueOf(params[1]);
        memid = Long.valueOf(params[2]);
        starttime = LocalDateTime.parse(params[3], formatter);
        slots = Integer.valueOf(params[4]);
    }

    public BookingEntity() {

    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getBookid() {
        return bookid;
    }
}
