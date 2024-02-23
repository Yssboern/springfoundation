package com.learn.springfoundation.repository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sql")
@AllArgsConstructor
public class SQLController {

    private final SqlDao sqlDao;
    private final MemberDAO memberDAO;
    private final FacilityDAO facilityDAO;
    private final TrainerDAO trainerDAO;
    private final BookingDAO bookingDAO;


    @GetMapping("/one")
    String get1() {
        return sqlDao.one();
    }

    @GetMapping("/two")
    String two() {
        memberDAO.getMembersListing();
        return "blep";
    }

    @GetMapping("/members")
    List<MemberDTO> members() {
        return memberDAO.getMembers();
    }

    @GetMapping("/member/{id}")
    MemberDTO member(@PathVariable Long id) {
        return memberDAO.getById(id);
    }

    @GetMapping("/facilities")
    List<FacilityDTO> getFacilities() {
        return facilityDAO.getAll();
    }

    @GetMapping("facility/{id}")
    FacilityDTO getFacility(@PathVariable Long id) {
        return facilityDAO.getById(id);
    }

    @GetMapping("/trainers")
    List<TrainerDTO> getTrainers() {
        return trainerDAO.getAll();
    }

    @GetMapping("trainer/{id}")
    TrainerDTO getTrainer(@PathVariable Long id) {
        return trainerDAO.getById(id);
    }

    @GetMapping("/fast/bookings")
    List<BookingDTO> getFastBookings() {
        return bookingDAO.getAll();
    }

    @GetMapping("/booking/{id}")
    BookingDTO getBooking(@PathVariable Long id) {
        return bookingDAO.getById(id);
    }

}
