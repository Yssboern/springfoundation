package com.learn.springfoundation.errsave;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
class TaskManager {

    private final JobTaskRepo repo;
    private final TaskLogger taskLogger;

    List<JobTask> prepareTasks(Integer n) {

        System.out.printf("Start preparation of %d tasks%n", n);
        List<JobTask> newTasks = new ArrayList<>(n);
        List<JobTask> tasks = new ArrayList<>(n);

        while (n-- > 0) {
            var e = new JobTask(null, "Job task no: " + n, "PENDING", 0);
            newTasks.add(e);
        }
        var saved = repo.saveAll(newTasks);
        saved.forEach(tasks::add);
        tasks.forEach(System.out::println);
        return tasks;
    }

    void runTasks() {
        var tasks = repo.findAll();
        tasks.forEach(this::processTask);
    }

    void processTask(JobTask task) {
        try {
            if (task.getDescription().contains("13")) {
                throw new Exception("13");
            }
            task.setResult("DONE");
            repo.save(task);
        } catch (Exception e) {
            task.setResult("ERROR");
            taskLogger.setTaskError(task);
            repo.save(task);
        }
    }
}
