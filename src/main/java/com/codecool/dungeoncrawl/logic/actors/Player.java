package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Player extends Actor {

    public Player(Cell cell, int attackStrength) {
        super(cell, attackStrength);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.ITEM || nextCell.getType() == CellType.KEY){
            cell.setActor(null);
            cell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            nextCell.setType(CellType.PLAYER);
            cell = nextCell;
        } else if (nextCell.getType() == CellType.SKELETON) {
            if (nextCell.getActor().health > 0) {
                nextCell.getActor().health -= attackStrength;
                health -= nextCell.getActor().attackStrength;
                if (nextCell.getActor().health <= 0) {
                    nextCell.setActor(null);
                    nextCell.setType(CellType.FLOOR);
                }
            } else if (nextCell.getActor().health == 0) {
                cell.setActor(null);
                cell.setType(CellType.FLOOR);
                nextCell.setActor(this);
                nextCell.setType(CellType.PLAYER);
                cell = nextCell;
            }
        }
    }

    public String getTileName() {
        return "player";
    }
}
