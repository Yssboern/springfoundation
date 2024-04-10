package com.learn.springfoundation.errsave;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JobTaskRepo extends CrudRepository<JobTask, Long> {
}
