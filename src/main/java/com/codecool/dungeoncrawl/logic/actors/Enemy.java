package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Enemy extends Actor{

    public Enemy(Cell cell, int attackStrength) {
        super(cell, attackStrength);
    }

}
