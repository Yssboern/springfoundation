package com.learn.springfoundation.facility;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilitiesRepo extends CrudRepository<Facility, Long> {

    Page<Facility> findAll(Pageable pageable);
}
