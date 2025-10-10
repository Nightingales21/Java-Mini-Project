package com.tamagotchi.items;
import com.tamagotchi.core.Box;

public class Toy extends Box{
    private String name;

    public Toy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
