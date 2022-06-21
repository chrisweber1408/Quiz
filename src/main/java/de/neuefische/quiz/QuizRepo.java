package de.neuefische.quiz;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizRepo extends MongoRepository<Question, String> {

    List<Question> findAllByApproved(boolean approvalStatus);

}


