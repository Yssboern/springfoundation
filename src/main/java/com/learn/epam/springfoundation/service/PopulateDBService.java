package com.learn.epam.springfoundation.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.learn.epam.springfoundation.App;
import com.learn.epam.springfoundation.entity.BookingEntity;
import com.learn.epam.springfoundation.entity.FacilityEntity;
import com.learn.epam.springfoundation.entity.MemberEntity;
import com.learn.epam.springfoundation.repository.AppRepo;
import com.learn.epam.springfoundation.repository.BookingsRepo;
import com.learn.epam.springfoundation.repository.FacilitiesRepo;
import com.learn.epam.springfoundation.repository.MembersRepo;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class PopulateDBService {

    Logger logger = LoggerFactory.getLogger(PopulateDBService.class.getName());

    private DataSource dataSource;
    private MembersRepo membersRepo;
    private FacilitiesRepo facilitiesRepo;
    private BookingsRepo bookingsRepo;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    @PostConstruct
    private void populateMembers() throws IOException {
        Path path = Paths.get("src/main/resources/members.csv");
        Files.readAllLines(path)
                .stream()
                .skip(1) //skip header
                .map(line -> new MemberEntity(line.split(";")))
                .forEach(membersRepo::save);
        logger.info("Members loaded to DB");
    }

    @PostConstruct
    private void populateFacilities() throws IOException {
        Path path = Paths.get("src/main/resources/facilities.csv");
        Files.readAllLines(path)
                .stream()
                .skip(1) //skip header
                .map(line -> new FacilityEntity(line.split(";")))
                .forEach(facilitiesRepo::save);

        logger.info("Facilities loaded to DB");
    }

    @PostConstruct
    private void populateBookings() throws IOException {
        Path path = Paths.get("src/main/resources/bookings.csv");
        Files.readAllLines(path)
                .stream()
                .skip(1)
                .map(line -> new BookingEntity(line.split(";")))
                .forEach(bookingsRepo::save);
        logger.info("Bookings loaded to DB");
    }

}
