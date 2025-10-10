package com.tamagotchi.items;
import com.tamagotchi.core.Box;

public class Food extends Box{
    private String name;
    private int nutrition;

    public Food(String name, int nutrition) {
        this.name = name;
        this.nutrition = nutrition;
    }

    public String getName() {
        return name;
    }

    public int getNutrition() {
        return nutrition;
    }
}
