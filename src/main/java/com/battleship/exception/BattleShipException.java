package com.battleship.exception;

import lombok.Data;

@Data
public class BattleShipException extends RuntimeException{

    private final ErrorCode errorCode;

    public BattleShipException(String message, ErrorCode errorCode){
        super(message);
        if (errorCode == null) {
            throw new IllegalArgumentException("An error code must be provided!");
        }
        this.errorCode = errorCode;
    }
}
