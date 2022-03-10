package com.codecool.dungeoncrawl.logic;

public enum CellType {

    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SKELETON("skeleton"),
    PLAYER("player"),
    COIN("coin"),
    CLOSED_DOOR("closed_door"),
    CLOSED_DOOR_2("closed_door_2"),
    OPENED_DOOR("opened_door"),
    OPENED_DOOR_2("opened_door_2"),
    KEY("key_1"),
    KEY_2("key_2"),
    WATER("water");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }

}
