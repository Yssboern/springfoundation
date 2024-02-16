package com.learn.springfoundation.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BookingsRepo extends CrudRepository<BookingEntity, Long> {
}
