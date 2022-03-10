package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Enemy;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;

public class GameMap {

    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemy (Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy (Enemy enemy) {
        enemies.remove(enemy);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
