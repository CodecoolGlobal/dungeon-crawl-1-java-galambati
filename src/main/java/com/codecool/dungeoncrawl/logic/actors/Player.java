package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

    private List<String> inventory = new ArrayList<>();

    public Player(Cell cell, int attackStrength) {
        super(cell, attackStrength);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.FLOOR && cell.getType() == CellType.OPENED_DOOR) {
            cell.setActor(null);
            cell.setType(CellType.OPENED_DOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.COIN || nextCell.getType() == CellType.KEY || nextCell.getType() == CellType.OPENED_DOOR) {
            cell.setActor(null);
            cell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            if (nextCell.getType() != CellType.OPENED_DOOR){
                nextCell.setType(CellType.PLAYER);
            }
            cell = nextCell;
        } else if (nextCell.getType() == CellType.CLOSED_DOOR && inventory.contains("key")) {
            nextCell.setType(CellType.OPENED_DOOR);
        } else if (nextCell.getType() == CellType.SKELETON || nextCell.getType() == CellType.SCORPION) {
            if (nextCell.getActor().health > 0) {
                nextCell.getActor().health -= attackStrength;
                health -= nextCell.getActor().attackStrength;
                if (health <= 0) {
                    System.exit(0);
                }
                if (nextCell.getActor().health <= 0) {
                    nextCell.getGameMap().removeEnemy((Enemy) nextCell.getActor());
                    nextCell.setType(CellType.FLOOR);
                    nextCell.setActor(null);
                }
            }
        }
    }

    public String getTileName() {
        return "player";
    }

    public List<String> getInventory() {
        return this.inventory;
    }

    public void addToInventory(String item) {
        inventory.add(item);
    }
}
