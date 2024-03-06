package com.learn.springfoundation.trainer;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TrainingRepo extends CrudRepository<Training, Long> {

    Page<Training> findAll(Pageable pageable);
}
