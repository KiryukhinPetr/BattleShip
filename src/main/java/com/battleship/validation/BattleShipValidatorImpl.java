package com.battleship.validation;

import com.battleship.exception.BattleShipException;
import com.battleship.exception.ErrorCode;
import com.battleship.model.Point;
import com.battleship.services.BattleShipCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BattleShipValidatorImpl implements BattleShipValidator {
    @Autowired
    private BattleShipCacheService battleShipCacheService;
    private static final String BOARD_SIZE_OVER_LIMITTED = "Board size over limitted";
    private static final String SHIP_ELEMENT_IS_ALREADY_EXIST = "Ship element is already exist";
    @Override
    public void validateByBoardSize(int boardSize, Point point) {
        if ((point.getX() > boardSize) || (point.getY() > boardSize)){
            ErrorCode errorCode = new ErrorCode(HttpStatus.BAD_REQUEST, BOARD_SIZE_OVER_LIMITTED);
            throw new BattleShipException(BOARD_SIZE_OVER_LIMITTED, errorCode);
        }
    }

    @Override
    public void validateIfAlreadyExist(Point point) {
        if (battleShipCacheService.getShipElement(point) != null) {
            ErrorCode errorCode = new ErrorCode(HttpStatus.BAD_REQUEST, SHIP_ELEMENT_IS_ALREADY_EXIST);
            throw new BattleShipException(SHIP_ELEMENT_IS_ALREADY_EXIST, errorCode);
        }
    }
}
