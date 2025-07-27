package com.question.controller;

import com.question.entities.Question;
import com.question.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    // create question
    @PostMapping
    public Question createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }

    // get all question
    @GetMapping
    public List<Question> getAllQuestion(){
        return questionService.getAllQuestion();
    }

    // get question by id
    @GetMapping("/{id}")
    public Question getQuestion(@PathVariable Long id){
        return  questionService.getQuestionById(id);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionOfQuiz(@PathVariable Long quizId){
        return questionService.getQuestionsOfQuiz(quizId);
    }
}
