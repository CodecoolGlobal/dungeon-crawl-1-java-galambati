package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Scorpion extends Enemy {

    public Scorpion(Cell cell, int attackStrength) {
        super(cell, attackStrength);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.FLOOR) {
            cell.setActor(null);
            cell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            nextCell.setType(CellType.PLAYER);
            cell = nextCell;
        } else if (nextCell.getType() == CellType.PLAYER) {
            System.out.println(nextCell.getActor());
            if (nextCell.getActor().health > 0) {
                nextCell.getActor().health -= attackStrength;
                health -= nextCell.getActor().attackStrength;
                if (health <= 0) {
                    cell.setType(CellType.FLOOR);
                    cell.setActor(null);
                    cell.getGameMap().removeEnemy(this);
                }
                if (nextCell.getActor().health <= 0) {
                    System.exit(0);
                }
            }

        }
    }

    @Override
    public String getTileName() {
        return "scorpion";
    }
}
