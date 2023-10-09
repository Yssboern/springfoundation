package com.learn.epam.springfoundation.repository;

import com.learn.epam.springfoundation.entity.AppEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepo extends CrudRepository<AppEntity, Long> {

}
