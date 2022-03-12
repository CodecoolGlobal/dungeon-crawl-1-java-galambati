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
        if (nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.WALL) {
            cell.setActor(null);
            if (cell.getType() == CellType.WALL && nextCell.getType() == CellType.FLOOR){
                cell.setType(CellType.WALL);
            } else if (cell.getType() == CellType.FLOOR)
            nextCell.setActor(this);
            nextCell.setType(CellType.GHOST);
            cell = nextCell;
        } else if (nextCell.getType() == CellType.PLAYER && nextCell.getGameMap().getPlayer().getInventory2().containsKey("sword")) {
            nextCell.getGameMap().getPlayer().removeFromInventory2("sword");
            System.out.println(nextCell.getGameMap().getPlayer().getInventory2());
            nextCell.getGameMap().getPlayer().setAttackStrength(nextCell.getGameMap().getPlayer().getAttackStrength() - 2);
        }
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

//    @Override
//    public void move(int dx, int dy) {
//        Cell nextCell = cell.getNeighbor(dx, dy);
//        System.out.println(cell.getType());
//        System.out.println(nextCell.getType());
//        if (nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.WALL) {
//            cell.setActor(null);
//            if (cell.getType() == CellType.WALL && nextCell.getType() == CellType.FLOOR) {
//                System.out.println("wall to floor");
//                cell.setType(CellType.WALL);
//            } else if (cell.getType() == CellType.FLOOR && nextCell.getType() == CellType.WALL) {
//                System.out.println("floor to wall");
//                cell.setType(CellType.FLOOR);
//            } else {
//                cell.setType(nextCell.getType());
//                System.out.println("same");
//            }
//            nextCell.setActor(this);
////            nextCell.setType(CellType.GHOST); eznemkell
//            cell = nextCell;
//        } else if (nextCell.getType() == CellType.PLAYER && nextCell.getGameMap().getPlayer().getInventory2().containsKey("sword")) {
//            nextCell.getGameMap().getPlayer().removeFromInventory2("sword");
//            nextCell.getGameMap().getPlayer().setAttackStrength(nextCell.getGameMap().getPlayer().getAttackStrength() - 2);
//        }
//    }
}
