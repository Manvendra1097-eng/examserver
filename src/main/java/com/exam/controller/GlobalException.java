package com.exam.controller;

import com.exam.helper.ResponseData;
import com.exam.helper.UserFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ResponseData> exceptionHandler(UserFoundException ex){
        return new ResponseEntity<ResponseData>(new ResponseData(ex.getMessage(),false), HttpStatus.BAD_REQUEST);
    }

}
