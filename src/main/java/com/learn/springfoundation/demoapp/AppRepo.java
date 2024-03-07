package com.learn.springfoundation.demoapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AppRepo extends CrudRepository<TstEntity, Long> {

}
