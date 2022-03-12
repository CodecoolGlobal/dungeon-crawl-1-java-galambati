package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Scorpion;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Coin;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.logic.items.Key_2;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    private static InputStream levelSelector(int level){
        String level1 = "/map.txt";
        String level2 = "/map2.txt";
        if (level == 1){
            return MapLoader.class.getResourceAsStream(level1);
        } else {
            return MapLoader.class.getResourceAsStream(level2);
        }
    }

    public static GameMap loadMap(int level) {


        Scanner scanner = new Scanner(levelSelector(level));
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case 'q':
                            cell.setType(CellType.APPLE);
                            break;
                        case 'z':
                            cell.setType(CellType.CLOSED_DOOR_2);
                            break;
                        case 'n':
                            cell.setType(CellType.KEY_2);
                            new Key_2(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.OPENED_DOOR_2);
                            break;
                        case 'o':
                            cell.setType(CellType.OPENED_DOOR);
                            break;
                        case 'w':
                            cell.setType(CellType.WATER);
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case 'x':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        case 'i':
                            cell.setType(CellType.COIN);
                            new Coin(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.SWORD);
                            new Sword(cell);
                            break;
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.SKELETON);
                            map.addEnemy(new Skeleton(cell, 2));
                            break;
                        case 'r':
                            cell.setType(CellType.SCORPION);
                            map.addEnemy(new Scorpion(cell, 3));
                            break;
                        case 'h':
                            cell.setType(CellType.GHOST);
                            map.addEnemy(new Ghost(cell, 0));
                            break;
                        case '@':
                            cell.setType(CellType.PLAYER);
                            map.setPlayer(new Player(cell, 5));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
