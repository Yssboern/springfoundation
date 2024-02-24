package com.learn.springfoundation.repository;

import com.learn.springfoundation.trainer.Trainer;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TrainerRepo extends CrudRepository<Trainer, Long> {

    Page<Trainer> findAll(Pageable pageable);
}
