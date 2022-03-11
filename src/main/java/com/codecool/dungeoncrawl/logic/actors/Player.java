package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player extends Actor {

    private List<String> inventory = new ArrayList<>();
    private HashMap<String, Integer> inventory2 = new HashMap<>();

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
        }
        if (nextCell.getType() == CellType.FLOOR ||
                nextCell.getType() == CellType.COIN ||
                nextCell.getType() == CellType.KEY ||
                nextCell.getType() == CellType.OPENED_DOOR ||
                nextCell.getType() == CellType.OPENED_DOOR_2 ||
                nextCell.getType() == CellType.KEY_2 ||
                nextCell.getType() == CellType.SWORD ||
                nextCell.getType() == CellType.OPENED_DOOR_3) {
            if (nextCell.getType() == CellType.OPENED_DOOR_3) {
                System.exit(0);
            }
            cell.setActor(null);
            cell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            if (nextCell.getType() != CellType.OPENED_DOOR && nextCell.getType() != CellType.OPENED_DOOR_2){
                nextCell.setType(CellType.PLAYER);
            }
            if (nextCell.getType() == CellType.OPENED_DOOR || nextCell.getType() == CellType.OPENED_DOOR_2) {
                nextCell.setActor(this);
            }
            cell = nextCell;
        } else if (nextCell.getType() == CellType.CLOSED_DOOR && inventory.contains("key 1")) {
            nextCell.setType(CellType.OPENED_DOOR);
        } else if (nextCell.getType() == CellType.CLOSED_DOOR_2 && inventory.contains("key 2")) {
            nextCell.setType(CellType.OPENED_DOOR_3);
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
            System.out.println(cell.getActor());
            System.out.println("bejon");
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

    public HashMap<String, Integer> getInventory2() {
        return inventory2;
    }

    public void setInventory2(HashMap<String, Integer> inventory2) {
        this.inventory2 = inventory2;
    }

    public void addToInventory2(String item) {
        if (!inventory2.containsKey(item)){
            inventory2.put(item, 1);
            System.out.println(inventory2);
        } else {
            inventory2.put(item, inventory2.get(item) + 1);
            System.out.println(inventory2);
        }
    }

    public void removeFromInventory2(String item) {
        for (String i : inventory2.keySet()) {
            if (i.equals(item)){
                inventory2.put(item ,inventory2.get(item) - 1);
                if (inventory2.get(item) < 1){
                    inventory2.remove(item);
                }
            }
        }
    }
}
