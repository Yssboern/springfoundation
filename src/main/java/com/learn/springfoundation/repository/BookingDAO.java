package com.learn.springfoundation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@AllArgsConstructor
public class BookingDAO {

    private static final String bookingsCount = "SELECT COUNT(booking) FROM Booking booking";
    private static final String bookingsWithNames =
            "SELECT NEW com.learn.springfoundation.repository.BookingView(booking.bookid, m.firstname, m.surname, f.name, booking.starttime, booking.slots) " +
                    "FROM Booking booking " +
                    "JOIN booking.memid m ON booking.memid.memid=m.memid " +
                    "JOIN booking.facid f ON booking.facid.facid=f.facid ";

    private final BookingsRepo repo;
    private final EntityManager entityManager;

    List<BookingDTO> getAll() {
        var ff = repo.findAll();
        List<BookingDTO> bookings = new ArrayList<>();
        ff.forEach(facilityEntity -> bookings.add(toDTO(facilityEntity)));
        return bookings;
    }

    public Page<BookingDTO> getAllPaginated(PageRequest of) {
        return repo.findAll(of)
                .map(this::toDTO);
    }

    public BookingDTO getById(Long id) {
        Optional<Booking> optionalBooking = repo.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            return toDTO(booking);
        } else {
            throw new EntityNotFoundException("Booking with id " + id + " not found");
        }
    }

    private BookingDTO toDTO(Booking entity) {
        return new BookingDTO(
                entity.getBookid(),
                entity.getFacid().getFacid(), // Assuming facid is Long in FacilityEntity
                entity.getMemid().getMemid(), // Assuming memid is Long in MemberEntity
                entity.getStarttime(),
                entity.getSlots()
        );
    }

    List<BookingView> getBookingViews() {
        return entityManager.createQuery(bookingsWithNames, BookingView.class).getResultList();
    }

    public Page<BookingView> getPaginatedBookingViews(PageRequest pageRequest) {

        int offset = pageRequest.getPageNumber() * pageRequest.getPageSize();

        List<BookingView> resultList = entityManager.createQuery(bookingsWithNames, BookingView.class)
                .setFirstResult(offset)
                .setMaxResults(pageRequest.getPageSize())
                .getResultList();

        long totalCount = entityManager.createQuery(bookingsCount, Long.class).getSingleResult();
        return new PageImpl<>(resultList, pageRequest, totalCount);
    }

}
