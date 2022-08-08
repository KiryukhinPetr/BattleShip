package com.battleship.controllers;

import com.battleship.exception.BattleShipException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ResponseExceptionHandler {
    @ExceptionHandler(BattleShipException.class)
    ResponseEntity<String> handleBattleShipException(BattleShipException e) {
        return new ResponseEntity<>(e.getMessage(), e.getErrorCode().getHttpStatus());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResult errorResult = new ErrorResult();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorResult.getFieldErrors()
                    .add(new FieldValidationError(fieldError.getField(),
                            fieldError.getDefaultMessage()));
        }
        return errorResult;
    }
    @Getter
    @NoArgsConstructor
    static class ErrorResult {
        private final List<FieldValidationError> fieldErrors = new ArrayList<>();
        ErrorResult(String field, String message){
            this.fieldErrors.add(new FieldValidationError(field, message));
        }
    }

    @Getter
    @AllArgsConstructor
    static class FieldValidationError {
        private String field;
        private String message;
    }
}
