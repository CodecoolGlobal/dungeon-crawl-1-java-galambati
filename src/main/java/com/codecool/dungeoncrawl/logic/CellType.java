package com.codecool.dungeoncrawl.logic;

public enum CellType {

    EMPTY("empty", "no"),
    FLOOR("floor", "yes"),
    WALL("wall", "no"),
    SKELETON("skeleton", "no"),
    SCORPION("scorpion", "no"),
    GHOST("ghost", "no"),
    PLAYER("player", "no"),
    COIN("coin", "yes"),
    SWORD("sword", "yes"),
    CLOSED_DOOR("closed_door", "no"),
    CLOSED_DOOR_2("closed_door_2", "no"),
    OPENED_DOOR("opened_door", "yes"),
    OPENED_DOOR_2("opened_door_2", "yes"),
    OPENED_DOOR_3("opened_door_3", "yes"),
    KEY("key 1", "yes"),
    KEY_2("key 2", "yes"),
    WATER("water", "no"),
    APPLE("apple", "yes");

    private final String tileName;
    private final String isStepAble;

    CellType(String tileName, String isStepAble) {
        this.tileName = tileName;
        this.isStepAble = isStepAble;
    }

    public String getTileName() {
        return tileName;
    }

    public String getIsStepAble(){
        return isStepAble;
    }

}
