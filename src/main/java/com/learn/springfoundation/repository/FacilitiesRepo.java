package com.learn.springfoundation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilitiesRepo extends CrudRepository<Facility, Long> {
}