package com.learn.springfoundation.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//bookid;facid;memid;starttime;slots
@Entity
@Table(schema = "tgn", name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookid", nullable = false)
    private Long bookid;
    @ManyToOne
    private Facility facid;
    @ManyToOne
    private Member memid;
    private LocalDateTime starttime;
    private Integer slots;

    public Booking(Long bookid, Facility facid, Member memid, LocalDateTime starttime, Integer slots) {
        this.bookid = bookid;
        this.facid = facid;
        this.memid = memid;
        this.starttime = starttime;
        this.slots = slots;
    }

}
