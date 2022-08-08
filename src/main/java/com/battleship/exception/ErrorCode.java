package com.battleship.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ErrorCode {
    private HttpStatus httpStatus;
    private String description;
}
