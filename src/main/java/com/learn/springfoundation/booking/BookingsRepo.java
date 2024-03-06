package com.learn.springfoundation.booking;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BookingsRepo extends CrudRepository<Booking, Long> {

    Page<Booking> findAll(Pageable pageable);
}
