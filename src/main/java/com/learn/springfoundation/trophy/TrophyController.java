package com.learn.springfoundation.trophy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TrophyController {

    @Autowired
    private TrophyDAO dao;

    @GetMapping("/trophies")
    public Page<TrophyDTO> getAllPaginated(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return dao.getPaginatedTrophies(PageRequest.of(page - 1, size));
    }

    @GetMapping("/trophies/{id}")
    public TrophyDTO getById(@PathVariable Long id) {
        return dao.getById(id);
    }

    @PostMapping("/trophies")
    public ResponseEntity<TrophyDTO> createTrophy(@RequestBody NewTrophy trophy) {
        var createdTrophy = dao.createNewTrophy(trophy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrophy);
    }
}
