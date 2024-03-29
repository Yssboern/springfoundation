package com.learn.springfoundation.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
class BookingController {

    @Autowired
    private BookingDAO bookingDAO;

    @GetMapping("/bookings")
    public Page<BookingDTO> getBookings(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return bookingDAO.getAllPaginated(PageRequest.of(page, size));
    }

    @GetMapping("/bookings/views/all")
    public List<BookingView> getBookingViews() {
        return bookingDAO.getBookingViews();
    }

    @GetMapping("/bookings/views")
    public Page<BookingView> getPaginatedBookingViews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return bookingDAO.getPaginatedBookingViews(PageRequest.of(page, size));
    }

    @GetMapping("/bookings/{id}")
    public BookingDTO getBooking(@PathVariable Long id) {
        return bookingDAO.getById(id);
    }

//    @PostMapping("/bookings")
//    public ResponseEntity<BookingDTO> createBooking(@RequestBody NewBooking booking) {
//        // Assuming NewBooking is a DTO containing the necessary information for creating a new booking
//        BookingDTO newBooking = new BookingDTO();
//        newBooking.setFacid(booking.getFacilityId());
//        newBooking.setMemid(booking.getMemberId());
//        newBooking.setStarttime(LocalDateTime.parse(booking.getStartTime())); // Assuming startTime is a String in ISO format
//        newBooking.setSlots(booking.getSlots());
//
//        BookingDTO createdBooking = bookingDAO.createNewBooking(newBooking);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
//    }
}
