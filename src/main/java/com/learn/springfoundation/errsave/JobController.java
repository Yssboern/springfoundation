package com.learn.springfoundation.errsave;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/job")
class JobController {

    private final TaskManager taskManager;

    @GetMapping("/run")
    ResponseEntity<?> run() {
        taskManager.runTasks();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/prepare/{n}")
    ResponseEntity<List<JobTask>> prepareTasks(@PathVariable Integer n) {
        var tasks = taskManager.prepareTasks(n);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

}
