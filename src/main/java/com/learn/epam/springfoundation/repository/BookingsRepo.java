package com.learn.epam.springfoundation.repository;

import com.learn.epam.springfoundation.entity.BookingEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookingsRepo extends CrudRepository<BookingEntity, Long> {
}
