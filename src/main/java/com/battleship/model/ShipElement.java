package com.battleship.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ShipElement {
    private Point point;

    private boolean hitted;

    private UUID shipId;

}
