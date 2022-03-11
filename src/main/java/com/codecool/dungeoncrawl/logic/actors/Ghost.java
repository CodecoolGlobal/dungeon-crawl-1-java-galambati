package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Ghost extends Enemy {

    public Ghost(Cell cell, int attackStrength) {
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
        } else if (nextCell.getType() == CellType.PLAYER && nextCell.getGameMap().getPlayer().getInventory().contains("sword")) {
            nextCell.getGameMap().getPlayer().removeFromInventory("sword");
            nextCell.getGameMap().getPlayer().setAttackStrength(nextCell.getGameMap().getPlayer().getAttackStrength() - 2);
        }
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

}
