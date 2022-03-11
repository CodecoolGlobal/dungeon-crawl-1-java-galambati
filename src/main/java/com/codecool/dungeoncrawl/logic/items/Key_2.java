package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key_2 extends Item{
    public Key_2(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName(){
        return "key 2";
    }
}
