package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Add category
    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        Category category1 = this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

    // Get Category
    @GetMapping("/{cId}")
    public Category getCategory(@PathVariable("cId") Long categoryId){
        return this.categoryService.getCategory(categoryId);
    }

    // Get Categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories(Long categoryId){
        return  ResponseEntity.ok(this.categoryService.getCategories());
    }

    // update
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return this.categoryService.updateCategory(category);
    }

    // delete
    @DeleteMapping("/{cId}")
    public void deleteCategory(@PathVariable("cId") Long cId){
        this.categoryService.deleteCategory(cId
        );
    }
}
