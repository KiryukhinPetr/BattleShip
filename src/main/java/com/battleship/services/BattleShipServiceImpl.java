package com.battleship.services;

import com.battleship.dto.AddShipRequest;
import com.battleship.model.*;
import com.battleship.validation.BattleShipValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Service
public class BattleShipServiceImpl implements BattleShipService {
    @Autowired
    private BattleShipCacheService battleShipCacheService;

    @Autowired
    private BattleShipValidator battleShipValidator;

    @Value("${boardship.size}")
    private int boardShipSize;

    @Override
    public Ship addShipToTheBoard(AddShipRequest request) {
        UUID shipId = UUID.randomUUID();
        List<ShipElement> shipElements = createShipElements(request, shipId);
        Ship ship = Ship.builder().shipElements(shipElements).id(shipId).build();
        shipElements.forEach(shipElement -> battleShipCacheService.addShipElement(shipElement));
        battleShipCacheService.addShip(ship);
        return ship;

    }

    @Override
    public AttackResult shootByPoint(Point point) {
        battleShipValidator.validateByBoardSize(boardShipSize, point);
        ShipElement shipElement = battleShipCacheService.getShipElement(point);
        if (shipElement == null)
            return AttackResult.MISSED;
        shipElement.setHitted(true);
        Ship ship = battleShipCacheService.getShip(shipElement.getShipId());
        return isShipAlive(ship) ? AttackResult.HIT : AttackResult.SUNK;
    }

    private List<ShipElement> createShipElements(AddShipRequest request, UUID shipId) {
        List<ShipElement> shipElements;
        Direction direction = request.getDirection();
        Point startPoint = request.getStartPoint();
        int size = request.getSize();
        int startPointX = startPoint.getX();
        int startPointY = startPoint.getY();
        if (direction.equals(Direction.HORIZONTAL)) {
            shipElements = IntStream.range(0, size)
                    .mapToObj(i ->
                            createShipElement(shipId, startPointX + i, startPointY)
                    )
                    .collect(toList());
        } else {
            shipElements = IntStream.range(0, size)
                    .mapToObj(i ->
                            createShipElement(shipId, startPointX, startPointY + i)
                    )
                    .collect(toList());
        }

        return shipElements;
    }

    private ShipElement createShipElement(UUID shipId, int x, int y) {
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        battleShipValidator.validateByBoardSize(boardShipSize, point);
        battleShipValidator.validateIfAlreadyExist(point);
        return ShipElement.builder().point(point).hitted(false).shipId(shipId).build();
    }

    private boolean isShipAlive(Ship ship) {
        return ship.getShipElements().stream().anyMatch(shipElement -> !shipElement.isHitted());
    }
}
