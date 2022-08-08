package com.battleship.services;

import com.battleship.dto.AddShipRequest;
import com.battleship.model.AttackResult;
import com.battleship.model.Point;
import com.battleship.model.Ship;

public interface BattleShipService {
    Ship addShipToTheBoard(AddShipRequest request);

    AttackResult shootByPoint(Point point);

}
