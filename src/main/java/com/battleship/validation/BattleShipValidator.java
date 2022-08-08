package com.battleship.validation;

import com.battleship.model.Point;
import org.springframework.stereotype.Service;

public interface BattleShipValidator {
    void validateByBoardSize(int boardSize, Point point);

    void validateIfAlreadyExist(Point point);
}
