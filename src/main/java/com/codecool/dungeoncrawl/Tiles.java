package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {

    public static int TILE_WIDTH = 32;
    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {

        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }

    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(19, 7));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("scorpion", new Tile(31, 5));
        tileMap.put("ghost", new Tile(27, 6));
        tileMap.put("coin", new Tile(22,4));
        tileMap.put("sword", new Tile(2,28));
        tileMap.put("closed_door", new Tile(4,9));
        tileMap.put("opened_door", new Tile(6, 9));
        tileMap.put("key 1", new Tile(16, 23));
        tileMap.put("water", new Tile(8, 5));
        tileMap.put("opened_door_2", new Tile(2, 9));
        tileMap.put("closed_door_2", new Tile(0,9));
        tileMap.put("key 2", new Tile(17, 23));
        tileMap.put("opened_door_3", new Tile(16, 8));
        tileMap.put("apple", new Tile(15, 29));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

}
