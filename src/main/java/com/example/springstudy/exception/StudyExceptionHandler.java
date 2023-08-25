package com.example.springstudy.exception;

import com.example.springstudy.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class StudyExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseDTO<String> globalExceptionHandler(Exception e) {
        return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
