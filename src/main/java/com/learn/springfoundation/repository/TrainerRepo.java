package com.learn.springfoundation.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TrainerRepo extends CrudRepository<TrainerEntity, Long> {

   // @EntityGraph(attributePaths = {"facilities"})
//    List<TrainerEntity> findAll();
}
