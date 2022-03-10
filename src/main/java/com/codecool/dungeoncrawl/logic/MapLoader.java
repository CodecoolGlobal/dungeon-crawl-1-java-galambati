package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Coin;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");

//        String text = new BufferedReader(
//                new InputStreamReader(is, StandardCharsets.UTF_8))
//                .lines()
//                .collect(Collectors.joining("\n"));
//
//        System.out.println(text);

        Scanner scanner = new Scanner(is);
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
                        case 'k':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case 'x':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        case 'i':
                            cell.setType(CellType.ITEM);
                            new Coin(cell);
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
                            new Skeleton(cell, 2);
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
