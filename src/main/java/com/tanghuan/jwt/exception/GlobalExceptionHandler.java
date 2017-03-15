package com.tanghuan.jwt.exception;


import com.tanghuan.jwt.entity.vo.RestRespBody;
import com.tanghuan.jwt.utils.RestRespStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * Created by tanghuan on 2017/3/8.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        RestRespBody body = new RestRespBody();
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            RestRespStatus status = RestRespStatus.getStatus(fieldError.getDefaultMessage());
            status = status != RestRespStatus.SUCCESS ? status : RestRespStatus.PARAM_NOT_RIGHT;
            body.changeStatus(status);
        });
        return new ResponseEntity(body, HttpStatus.OK);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity handleMethodArgumentBindException(BindException ex) {
        RestRespBody body = new RestRespBody();
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> body.changeStatus(RestRespStatus.getStatus(fieldError.getDefaultMessage())));
        return new ResponseEntity(body, HttpStatus.OK);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception ex) {
        System.err.println("《===================GlobalExceptionHandler.handleException===================》");
        System.err.println(ex.getMessage());
        return new ResponseEntity(ex, HttpStatus.OK);
    }

}
