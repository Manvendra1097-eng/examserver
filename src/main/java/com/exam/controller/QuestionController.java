package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;


    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Question question){
        Question question1 = this.questionService.addQuestion(question);
        return ResponseEntity.ok(question1);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateQuiz(@RequestBody Question question){
        Question question1 = this.questionService.updateQuestion(question);
        return ResponseEntity.ok(question1);
    }


    @GetMapping("/quiz/all/{quizId}")
    public ResponseEntity<?> getAllByQuiz(@PathVariable("quizId") Long quizId){

        Quiz quiz = this.quizService.getQuiz(quizId);
        Set<Question> question = quiz.getQuestion();

        List list = new ArrayList<>(question);

//        if(list.size()>Integer.parseInt(quiz.getNoOfQuestion())){
//            list = list.subList(0,Integer.parseInt(quiz.getNoOfQuestion()+1));
//        }
//        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getByQuiz(@PathVariable("quizId") Long quizId){

        Quiz quiz = this.quizService.getQuiz(quizId);
        Set<Question> question = quiz.getQuestion();

        List list = new ArrayList<>(question);

        if(list.size()>Integer.parseInt(quiz.getNoOfQuestion())){
            list = list.subList(0,Integer.parseInt(quiz.getNoOfQuestion()+1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{qId}")
    public Question getQuestion(@PathVariable("qId") Long qId){
        return this.questionService.getQuestion(qId);
    }

    @DeleteMapping("/{qId}")
    public void deleteQuestion(@PathVariable("qId") Long qId){
        this.questionService.deleteQuestion(qId);
    }

}
