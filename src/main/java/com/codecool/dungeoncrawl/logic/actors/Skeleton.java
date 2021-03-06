package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Enemy {

    public Skeleton(Cell cell, int attackStrength) {
        super(cell, attackStrength);
    }

    @Override
    public void move(int dx, int dy) {
        // Only enemy which does not moves.
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

}
