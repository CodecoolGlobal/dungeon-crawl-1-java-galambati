package com.codecool.dungeoncrawl.logic.items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    List<String> inventory = new ArrayList<>();

    public List<String> getInventory(){
        return this.inventory;
    }

    public void addToInventory(String item){
        inventory.add(item);
    }

}
