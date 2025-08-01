package com.question.repository;

import com.question.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // defining custom finder method
    List<Question> findByQuizId(Long quizId);
}
