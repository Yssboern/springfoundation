package com.learn.springfoundation.trainer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TrainerDetailsDAO {

    private final TrainerRepo trainerRepo;
    private final TrainersNotesRepo trainersNotesRepo;
    private final EntityManager entityManager;
    private final TrainerConverter converter;

    public TrainerDetails getById(Long id) {
        Optional<Trainer> optionalTrainer = trainerRepo.findById(id);
        if (optionalTrainer.isPresent()) {
            Trainer entity = optionalTrainer.get();
            TrainerDetails details = new TrainerDetails();
            details.setId(entity.getId());
            details.setFirstname(entity.getFirstname());
            details.setSurname(entity.getSurname());
            details.setFacilities(entity.getFacilities().stream().map(facility -> new IdName(facility.getFacid(), facility.getName())).toList());
            details.setSkills(entity.getSpecialisations().stream().map(skill -> new IdName(skill.getId(), skill.getName())).toList());
            details.setTrophies(entity.getTrophies().stream().map(trophy -> new IdName(trophy.getId(), trophy.getName())).toList());
            details.setTrophies(entity.getTrophies().stream().map(trophy -> new IdName(trophy.getId(), trophy.getName())).toList());
            details.setNotes(entity.getTrainersNotes().stream().map(trophy -> new IdName(trophy.getId(), trophy.getNote())).toList());
            return details;
        } else {
            throw new EntityNotFoundException("Trainer with id " + id + " not found");
        }
    }

    List<IdName> getTrainingDisplayList(Long trainerId) {
        String sql = "SELECT NEW com.learn.springfoundation.trainer.IdName(ts.id, ts.name) " +
                "FROM Trainer trainer " +
                "JOIN trainer.specialisations ts " +
                "WHERE trainer.id = :trainerId";

        List<IdName> results = entityManager.createQuery(sql, IdName.class)
                .setParameter("trainerId", trainerId)
                .getResultList();
        displayList(results);
        return results;
    }

    public List<IdName> getTrainersNotes(Long trainerId) {
        String sql = "SELECT NEW com.learn.springfoundation.trainer.IdName(tn.id, tn.note) " +
                "FROM Trainer trainer " +
                "JOIN trainer.trainersNotes tn " +
                "WHERE trainer.id = :trainerId";

        List<IdName> results = entityManager.createQuery(sql, IdName.class)
                .setParameter("trainerId", trainerId)
                .getResultList();
        displayList(results);
        return results;
    }

    private void displayList(List<IdName> results) {
        for (IdName info : results) {
            Long id = info.getId();
            String text = info.getName();
            System.out.println(id + " " + text);
        }
    }

    public IdName addTrainersNote(Long trainerId, IdName note) {
        var trainer = new Trainer(trainerId);
        var newNote = new TrainersNote(note.getName(), trainer);

        List<TrainersNote> notes = new ArrayList<>();
        notes.add(newNote);
        trainer.setTrainersNotes(notes);

        var r = trainersNotesRepo.save(newNote);
        return new IdName(r.getId(), r.getNote());
    }

    public TrainerDetails addTrainersNote2(Long trainerId, IdName note) {
        var trainer = trainerRepo.findById(trainerId).orElseThrow();
        var newNote = new TrainersNote(note.getName(), trainer);
        trainer.getTrainersNotes().add(newNote);

        var r = trainerRepo.save(trainer);
        return converter.toDetails(trainer);
    }

    public void deleteById(Long id) {
        trainerRepo.deleteById(id);
    }

}
