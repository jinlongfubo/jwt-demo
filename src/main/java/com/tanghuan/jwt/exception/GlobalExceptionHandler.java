package com.tanghuan.jwt.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by tanghuan on 2017/3/8.
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception ex) {
        System.err.println(ex.getMessage());
        System.err.println("《===================GlobalExceptionHandler.handleException===================》");
        return new ResponseEntity(ex, HttpStatus.OK);
    }

}
