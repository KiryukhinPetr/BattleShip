package com.battleship.model;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class Point {
    @Min(value = 0, message = "The value must be positive")
    private int x;
    @Min(value = 0, message = "The value must be positive")
    private int y;
}
