package com.learn.springfoundation.trainer;

import com.learn.springfoundation.IdText;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
class TrainerDetailsDAO {

    private final TrainerRepo trainerRepo;
    private final TrainersNotesRepo trainersNotesRepo;
    private final EntityManager entityManager;
    private final TrainerConverter converter;

    public TrainerDetails getById(Long id) {
        Optional<Trainer> optionalTrainer = trainerRepo.findById(id);
        if (optionalTrainer.isPresent()) {
            Trainer entity = optionalTrainer.get();
            return converter.toDetails(entity);
        } else {
            throw new EntityNotFoundException("Trainer with id " + id + " not found");
        }
    }

    List<IdText> getTrainingDisplayList(Long trainerId) {
        String sql = "SELECT NEW com.learn.springfoundation.IdText(ts.id, ts.name) " +
                "FROM Trainer trainer " +
                "JOIN trainer.specialisations ts " +
                "WHERE trainer.id = :trainerId";

        List<IdText> results = entityManager.createQuery(sql, IdText.class)
                .setParameter("trainerId", trainerId)
                .getResultList();
        displayList(results);
        return results;
    }

    public List<IdText> getTrainersNotes(Long trainerId) {
        String sql = "SELECT NEW com.learn.springfoundation.IdText(tn.id, tn.note) " +
                "FROM Trainer trainer " +
                "JOIN trainer.trainersNotes tn " +
                "WHERE trainer.id = :trainerId";

        List<IdText> results = entityManager.createQuery(sql, IdText.class)
                .setParameter("trainerId", trainerId)
                .getResultList();
        displayList(results);
        return results;
    }

    private void displayList(List<IdText> results) {
        for (IdText info : results) {
            Long id = info.id();
            String text = info.text();
            System.out.println(id + " " + text);
        }
    }

    public IdText addTrainersNote(Long trainerId, IdText note) {
        var trainer = new Trainer(trainerId);
        var newNote = new TrainersNote(note.text(), trainer);

        List<TrainersNote> notes = new ArrayList<>();
        notes.add(newNote);
        trainer.setTrainersNotes(notes);

        var r = trainersNotesRepo.save(newNote);
        return new IdText(r.getId(), r.getNote());
    }

    public TrainerDetails addTrainersNote2(Long trainerId, IdText note) {
        var trainer = trainerRepo.findById(trainerId).orElseThrow();
        var newNote = new TrainersNote(note.text(), trainer);
        trainer.getTrainersNotes().add(newNote);

        var r = trainerRepo.save(trainer);
        return converter.toDetails(trainer);
    }

    public void deleteById(Long id) {
        trainerRepo.deleteById(id);
    }

}
