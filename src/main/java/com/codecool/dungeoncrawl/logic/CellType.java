package com.codecool.dungeoncrawl.logic;

public enum CellType {

    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SKELETON("skeleton"),
    PLAYER("player"),
    ITEM("item");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }

}
