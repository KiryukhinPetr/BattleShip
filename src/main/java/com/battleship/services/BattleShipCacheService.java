package com.battleship.services;

import com.battleship.exception.BattleShipException;
import com.battleship.exception.ErrorCode;
import com.battleship.model.Point;
import com.battleship.model.Ship;
import com.battleship.model.ShipElement;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

@Service
public class BattleShipCacheService {
    private final ConcurrentHashMap<Point, ShipElement> shipElementsCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<UUID, Ship> shipsCache = new ConcurrentHashMap<>();
    public void addShipElement(ShipElement shipElement) {
        shipElementsCache.putIfAbsent(shipElement.getPoint(), shipElement);
    }

    public ShipElement getShipElement(Point point) {
        return shipElementsCache.get(point);
    }

    public void addShip(Ship ship) {
        shipsCache.putIfAbsent(ship.getId(), ship);
    }

    public Ship getShip(UUID id) {
        return shipsCache.get(id);
    }

}
