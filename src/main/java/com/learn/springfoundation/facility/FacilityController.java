package com.learn.springfoundation.facility;

import com.learn.springfoundation.IdText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
class FacilityController {

    @Autowired
    private FacilityDAO dao;

    //                const response = await fetch(`http://localhost:8080/api/facilities?page=${currentPage}`);
    @GetMapping("/facilities")
    public Page<FacilityDTO> getAllPaginated(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        System.out.println("http://localhost:8080/api/facilities?page=$" + page);
        var result = dao.getAllPaginated(PageRequest.of(page - 1, size));
        System.out.println("----------------------------------------------");
        return result;
    }

    @GetMapping("/facilities/selection")
    public Page<IdText> getAllPaginatedSelectionList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        System.out.println("http://localhost:8080/api/facilities/selection?page=$" + page);
        var result = dao.getAllPaginatedItemList(PageRequest.of(page - 1, size));
        System.out.println("----------------------------------------------");
        return result;
    }

    @GetMapping("/facilities/{id}")
    public FacilityDTO getById(@PathVariable Long id) {
        return dao.getById(id);
    }

//    @PostMapping("/facility")
//    public ResponseEntity<FacilityDTO> createTrainer(@RequestBody FacilityDTO facility) {
//        var createdTrainer = dao.create(facility);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainer);
//    }
}

