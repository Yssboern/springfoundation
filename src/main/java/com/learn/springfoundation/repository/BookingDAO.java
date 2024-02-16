package com.learn.springfoundation.repository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@AllArgsConstructor
public class BookingDAO {

    private final BookingsRepo bookingsRepo;

    List<BookingDTO> getAll() {
        var ff = bookingsRepo.findAll();
        List<BookingDTO> bookings = new ArrayList<>();
        ff.forEach(facilityEntity -> bookings.add(toDTO(facilityEntity)));
        return bookings;
    }

    public BookingDTO getById(Long id) {
        Optional<BookingEntity> optionalBooking = bookingsRepo.findById(id);
        if (optionalBooking.isPresent()) {
            BookingEntity bookingEntity = optionalBooking.get();
            return toDTO(bookingEntity);
        } else {
            throw new EntityNotFoundException("Booking with id " + id + " not found");
        }
    }

    private BookingDTO toDTO(BookingEntity entity) {
        return new BookingDTO(
                entity.getBookid(),
                entity.getFacid().getFacid(), // Assuming facid is Long in FacilityEntity
                entity.getMemid().getMemid(), // Assuming memid is Long in MemberEntity
                entity.getStarttime(),
                entity.getSlots()
        );
    }

}
