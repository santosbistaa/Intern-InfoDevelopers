package com.quiz.controller;

import com.quiz.entities.Quiz;
import com.quiz.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    // create quiz
    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    // get all quiz
    @GetMapping
    public List<Quiz> getAllQuiz(){
        return quizService.getAllQuiz();
    }

    // get quiz by id
    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.getQuiz(id);
    }
}
