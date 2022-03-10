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
        } else if (nextCell.getType() == CellType.FLOOR ||
                nextCell.getType() == CellType.COIN ||
                nextCell.getType() == CellType.KEY ||
                nextCell.getType() == CellType.OPENED_DOOR ||
                nextCell.getType() == CellType.OPENED_DOOR_2 ||
                nextCell.getType() == CellType.KEY_2 || nextCell.getType() == CellType.SWORD) {
            cell.setActor(null);
            cell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            if (nextCell.getType() != CellType.OPENED_DOOR || nextCell.getType() != CellType.OPENED_DOOR_2){
                nextCell.setActor(this);
            }
            cell = nextCell;
        } else if (nextCell.getType() == CellType.CLOSED_DOOR && inventory.contains("key_1")) {
            nextCell.setType(CellType.OPENED_DOOR);
        } else if (nextCell.getType() == CellType.CLOSED_DOOR_2 && inventory.contains("key_2")){
            nextCell.setType(CellType.OPENED_DOOR_2);
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
        } else if (nextCell.getType() == CellType.GHOST && inventory.contains("sword")) {
            removeFromInventory("sword");
        }
    }

    public String getTileName() {
        return "player";
    }

    public List<String> getInventory() {
        return this.inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public void addToInventory(String item) {
        inventory.add(item);
    }

    public void removeFromInventory(String item) {
        inventory.remove(item);
    }
}
