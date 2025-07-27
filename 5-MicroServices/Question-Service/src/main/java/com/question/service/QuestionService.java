package com.question.service;

import com.question.entities.Question;
import com.question.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id){
        return questionRepository.findById(id).orElseThrow(()-> new RuntimeException("Question not found"));
    }

    public List<Question> getQuestionsOfQuiz(Long quizId){
        return questionRepository.findByQuizId(quizId);
    }
}
