package com.battleship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class BattleShipApplication {
    public static void main(String[] args) {
        SpringApplication.run(BattleShipApplication.class, args);
    }
}
