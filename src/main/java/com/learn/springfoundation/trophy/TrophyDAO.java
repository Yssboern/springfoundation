package com.learn.springfoundation.trophy;

import com.learn.springfoundation.IdText;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
class TrophyDAO {

    private final TrophyRepo trophyRepo;

    @Autowired
    public TrophyDAO(TrophyRepo trophyRepo) {
        this.trophyRepo = trophyRepo;
    }

    public TrophyDTO toDTO(Trophy entity) {
        return new TrophyDTO(entity.getId(), entity.getName(), entity.getYear());
    }

    public TrophyDTO getById(Long id) {
        Optional<Trophy> optionalTrophy = trophyRepo.findById(id);
        if (optionalTrophy.isPresent()) {
            Trophy trophy = optionalTrophy.get();
            return toDTO(trophy);
        } else {
            throw new EntityNotFoundException("Trophy with id " + id + " not found");
        }
    }


    public Page<TrophyDTO> getPaginatedTrophies(PageRequest pageRequest) {
        return trophyRepo.findAll(pageRequest)
                .map(this::toDTO);
    }

    public Page<IdText> getPaginatedTrophiesSelectionList(PageRequest pageRequest) {
        return trophyRepo.findAll(pageRequest)
                .map(this::toIdText);
    }

    private IdText toIdText(Trophy entity) {
        return new IdText(entity.getId(), entity.getName());
    }

    public TrophyDTO createNewTrophy(NewTrophy newTrophy) {
        Trophy newEntity = new Trophy();
        newEntity.setName(newTrophy.name());
        newEntity.setYear(newTrophy.year());
//        var training = new Training();
//        training.setId(newTrophy.getId());
//        newEntity.setDiscipline(training);
        Trophy savedTrophy = trophyRepo.save(newEntity);
        log.info("New trophy created: " + savedTrophy.getName());
        return toDTO(savedTrophy);
    }

}
