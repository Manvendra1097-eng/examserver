package com.exam.service.impl;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

//    @Override
//    public List<Quiz> getQuizzes() {
//        return this.quizRepository.findAll();
//    }

    @Override
    public Page<Quiz> getQuizzes(int page, int size) {

        return this.quizRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public int getSize() {
        return this.quizRepository.findAll().size();
    }
//
//    @Override
//    public Page<Quiz> getQuizzes() {
//        return this.quizRepository.findAll(Pageable.unpaged());
//    }

    @Override
    public Quiz getQuiz(Long qId) {
        return this.quizRepository.findById(qId).get();
    }

    @Override
    public void deleteQuiz(Long id) {

        this.quizRepository.deleteById(id);
    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category category) {
        return this.quizRepository.findByCategory(category);
    }

    @Override
    public List<Quiz> getActiveQuizzes() {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category c) {
        return this.quizRepository.findByCategoryAndActive(c,true);
    }

}
