package com.learn.epam.springfoundation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepo extends CrudRepository<AppEntity, Long> {

}
