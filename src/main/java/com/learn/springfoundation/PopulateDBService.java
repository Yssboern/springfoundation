package com.learn.springfoundation;

import com.learn.springfoundation.booking.Booking;
import com.learn.springfoundation.booking.BookingsRepo;
import com.learn.springfoundation.facility.FacilitiesRepo;
import com.learn.springfoundation.facility.Facility;
import com.learn.springfoundation.member.Member;
import com.learn.springfoundation.member.MembersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
class PopulateDBService {

    private static final Logger logger = LoggerFactory.getLogger(PopulateDBService.class.getName());
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private MembersRepo membersRepo;
    private FacilitiesRepo facilitiesRepo;
    private BookingsRepo bookingsRepo;

    @Autowired
    public void setAppRepo(MembersRepo membersRepo) {
        this.membersRepo = membersRepo;
    }

    @Autowired
    public void setFacilitiesRepo(FacilitiesRepo facilitiesRepo) {
        this.facilitiesRepo = facilitiesRepo;
    }

    @Autowired
    public void setBookingsRepo(BookingsRepo bookingsRepo) {
        this.bookingsRepo = bookingsRepo;
    }

    //    @PostConstruct
    private void populateDB() throws IOException {
        populateMembers();
        populateFacilities();
        populateBookings();
    }

    private void populateMembers() throws IOException {
        Path path = Paths.get("src/main/resources/members.csv");
        Files.readAllLines(path)
                .stream()
                .skip(1) //skip header
                .map(line -> line.split(";"))
                .map(this::newMember)
                .forEach(membersRepo::save);
        logger.info("Members loaded to DB");
        membersRepo.findAll().forEach(System.out::println);
    }

    private Member newMember(String[] columns) {

        var memid = columns[0].equals("") ? null : Long.parseLong(columns[0]) + 1;
        var surname = columns[1];
        var firstname = columns[2];
        var address = columns[3];
        var zipcode = columns[4];
        var telephone = columns[5];

        Member recommendedby = null;
        if (!columns[6].isBlank()) {
            recommendedby = membersRepo.findById(Long.valueOf(columns[6])).orElse(null);
        }
        var joindate = LocalDateTime.parse(columns[7], formatter);
        return new Member(memid, surname, firstname, address, zipcode, telephone, recommendedby, joindate);
    }

    private void populateFacilities() throws IOException {
        Path path = Paths.get("src/main/resources/facilities.csv");
        Files.readAllLines(path)
                .stream()
                .skip(1) //skip header
                .map(line -> new Facility(line.split(";")))
                .forEach(facilitiesRepo::save);
        logger.info("Facilities loaded to DB");
    }

    private void populateBookings() throws IOException {
        Path path = Paths.get("src/main/resources/bookings.csv");
        var lines = Files.readAllLines(path);
        lines.stream()
                .skip(1)
                .map(line -> line.split(";"))
                .map(this::newBooking)
                .forEach(bookingsRepo::save);

        logger.info("Bookings loaded to DB");
    }

    private Booking newBooking(String[] params) {

        var bookid = Long.valueOf(params[0]);
        var facid = facilitiesRepo.findById(Long.parseLong(params[1]) + 1).orElse(null);
        var memid = membersRepo.findById(Long.parseLong(params[2]) + 1).orElse(null);
        var starttime = LocalDateTime.parse(params[3], formatter);
        var slots = Integer.valueOf(params[4]);

        return new Booking(bookid, facid, memid, starttime, slots);
    }
}