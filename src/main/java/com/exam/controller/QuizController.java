package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController {

    @Autowired
    private QuizService quizService;


    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz){
        Quiz quiz1= this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
        Quiz quiz1= this.quizService.updateQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }

//    @GetMapping("/")
//    public ResponseEntity<?> getQuizzes(){
//        return ResponseEntity.ok(this.quizService.getQuizzes());
//    }

    @GetMapping("/")
    public ResponseEntity<?> getQuizzes(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size
            ){
        int len = this.quizService.getSize();
        return ResponseEntity.ok(this.quizService.getQuizzes(page.orElse(0),size.orElse(len)));
    }

//    @GetMapping("/")
//    public ResponseEntity<?> getQuizzes(
//    ){
//        return ResponseEntity.ok(this.quizService.getQuizzes());
//    }

    @GetMapping("/{qId}")
    public Quiz getQuiz(@PathVariable("qId") Long qId){
        return this.quizService.getQuiz(qId);
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable("id") Long id){

        this.quizService.deleteQuiz(id);
    }

    @GetMapping("/category/{cId}")
    public ResponseEntity<?> getQuizzesOfCategory(@PathVariable("cId") Long cId){
        Category category = new Category();
        category.setcId(cId);
        return ResponseEntity.ok(this.quizService.getQuizzesOfCategory(category));
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveQuizzes(){
        return ResponseEntity.ok(this.quizService.getActiveQuizzes());
    }

    @GetMapping("/category/active/{cId}")
    public ResponseEntity<?> getActiveQuizzes(@PathVariable("cId") Long cId){
        Category category = new Category();
        category.setcId(cId);
        return ResponseEntity.ok(this.quizService.getActiveQuizzesOfCategory(category));
    }

}
