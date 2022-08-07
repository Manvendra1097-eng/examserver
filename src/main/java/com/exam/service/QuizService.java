package com.exam.service;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Page<Quiz> getQuizzes(int page, int size);

    public int getSize();
//    public List<Quiz> getQuizzes();

//    public Page<Quiz> getQuizzes();

    public Quiz getQuiz(Long qId);

    public void deleteQuiz(Long qId);

    List<Quiz> getQuizzesOfCategory(Category category);

    List<Quiz> getActiveQuizzes();

    List<Quiz> getActiveQuizzesOfCategory(Category c);
}
