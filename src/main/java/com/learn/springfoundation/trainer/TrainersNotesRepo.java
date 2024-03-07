package com.learn.springfoundation.trainer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface TrainersNotesRepo extends CrudRepository<TrainersNote, Long> {

    List<TrainersNote> getTrainersNoteByTrainer(Trainer trainer);
}
