package com.battleship.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Ship {
    UUID id;
    List<ShipElement> shipElements;
}
