package com.learn.springfoundation.trainer;

import com.learn.springfoundation.repository.TrainerConverter;
import com.learn.springfoundation.repository.TrainerRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TrainerDetailsDAO {

    private final TrainerRepo trainerRepo;
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
            return details;
        } else {
            throw new EntityNotFoundException("Trainer with id " + id + " not found");
        }
    }

    List<IdName> getTrainingDisplayList(Long trainerId) {
        List<IdName> resultList = entityManager.createQuery(
                        "SELECT NEW com.learn.springfoundation.trainer.IdName(ts.id, ts.name) " +
                                "FROM Trainer trainer " +
                                "JOIN trainer.specialisations ts " +
                                "WHERE trainer.id = :trainerId", IdName.class)
                .setParameter("trainerId", trainerId)
                .getResultList();

        for (IdName info : resultList) {
            Long trainingId = info.getId();
            String trainingName = info.getName();
            System.out.println(trainingId + " " + trainingName);
            // Process the result as needed
        }
        return resultList;
    }
}
