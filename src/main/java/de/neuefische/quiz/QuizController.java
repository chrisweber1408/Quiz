package de.neuefische.quiz;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RestController
@RequestMapping("/api/questions")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question postQuestion(@RequestBody Question question){
        return quizService.addOneQuestion(question);
    }

    @GetMapping
    public List<Question> getAllTrueQuestions(){
        return quizService.getApprovedQuestions();
    }

    @GetMapping("/{id}")
    public Optional<Question> getOneQuestion(@PathVariable String id){
        return quizService.getOne(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOneQuestion(@PathVariable String id){
        quizService.deleteOne(id);
    }

    @PutMapping
    public Question editOneQuestion(@PathVariable Question question){
        return quizService.editOne(question);
    }

}










