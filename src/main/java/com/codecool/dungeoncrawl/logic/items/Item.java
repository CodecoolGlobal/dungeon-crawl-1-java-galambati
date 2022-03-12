package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Item implements Drawable, Collectable {

    private Cell cell;

    public Item (Cell cell){
        this.cell = cell;
        this.cell.setItem(this);
    }

}
