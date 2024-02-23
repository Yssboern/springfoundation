package com.learn.springfoundation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class FacilityController {

    @Autowired
    private FacilityDAO dao;

//                const response = await fetch(`http://localhost:8080/api/facilities?page=${currentPage}`);
    @GetMapping("/facilities")
    public Page<FacilityDTO> getAllPaginated(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return dao.getAllPaginated(PageRequest.of(page - 1, size));
    }

    @GetMapping("/facilities/{id}")
    public FacilityDTO getById(@PathVariable Long id) {
        return dao.getById(id);
    }

//    @PostMapping("/facility")
//    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody NewTrainer trainer) {
//        var createdTrainer = dao.create(trainer);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainer);
//    }
}

