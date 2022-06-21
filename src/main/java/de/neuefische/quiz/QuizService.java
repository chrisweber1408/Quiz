package de.neuefische.quiz;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class QuizService {

    private final QuizRepo quizRepo;

    public QuizService(QuizRepo quizRepo) {
        this.quizRepo = quizRepo;
    }

    public Question addOneQuestion(Question questionToAdd){
        return quizRepo.save(questionToAdd);
    }

    public List<Question> getApprovedQuestions() {
        return quizRepo.findAllByApproved(true);
    }

    public Optional<Question> getOne(String id) {
        return quizRepo.findById(id);
    }

    public void deleteOne(String id) {
        quizRepo.deleteById(id);
    }

    public Question editOne(Question question) {
        return quizRepo.save(question);
    }
}
