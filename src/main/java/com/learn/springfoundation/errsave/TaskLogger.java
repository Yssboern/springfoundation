package com.learn.springfoundation.errsave;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
class TaskLogger {

    private final JobTaskRepo repo;

    void setTaskError(JobTask task) {
        repo.save(task);
    }

}
