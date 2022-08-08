package com.battleship.controllers;

import com.battleship.dto.AddShipRequest;
import com.battleship.model.AttackResult;
import com.battleship.model.Point;
import com.battleship.model.Ship;
import com.battleship.services.BattleShipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/ship")
@RestController
@Slf4j
public class BattleShipController {
    @Autowired
    private BattleShipService battleShipService;

    @PostMapping(
            value = "/adding",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Ship addShipToTheBoard(@Valid @RequestBody AddShipRequest request){
        log.info("Request: " + request);
        return battleShipService.addShipToTheBoard(request);
    }

    @PostMapping(
            value = "/shooting",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public AttackResult shootToThePoint(@Valid @RequestBody Point point){
        log.info("Try to shoot by input point: " + point);
        return battleShipService.shootByPoint(point);
    }
}
