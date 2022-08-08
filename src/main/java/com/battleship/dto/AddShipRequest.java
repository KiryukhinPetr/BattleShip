package com.battleship.dto;

import com.battleship.model.Direction;
import com.battleship.model.Point;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class AddShipRequest {
    private Point startPoint;

    private Direction direction;

    @Min(value = 0, message = "The value must be positive")
    private int size;
}
