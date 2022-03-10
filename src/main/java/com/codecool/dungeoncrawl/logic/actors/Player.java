package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Player extends Actor {

    public Player(Cell cell, int attackStrength) {
        super(cell, attackStrength);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.ITEM){
            cell.setActor(null);
            cell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            nextCell.setType(CellType.PLAYER);
            cell = nextCell;
        } else if (nextCell.getType() == CellType.SKELETON || nextCell.getType() == CellType.SCORPION) {
            System.out.println(nextCell.getActor());
            if (nextCell.getActor().health > 0) {
                nextCell.getActor().health -= attackStrength;
                health -= nextCell.getActor().attackStrength;
                System.out.println("tamadtam");
                if (health <= 0) {
                    System.exit(0);
                }
                if (nextCell.getActor().health <= 0) {
                    nextCell.setType(CellType.FLOOR);
                    nextCell.setActor(null);
                    nextCell.getGameMap().removeEnemy((Enemy) nextCell.getActor());
                }
            }
        }
    }

    public String getTileName() {
        return "player";
    }

}
