package com.learn.epam.springfoundation.repository;

import com.learn.epam.springfoundation.entity.FacilityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilitiesRepo extends CrudRepository<FacilityEntity, Long> {
}
