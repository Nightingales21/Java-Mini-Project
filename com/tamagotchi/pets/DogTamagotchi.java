package com.tamagotchi.pets;

import com.tamagotchi.core.Tamagotchi;

public class DogTamagotchi extends Tamagotchi {
    public DogTamagotchi() {
        this("Unnamed Dog");
    }

    public DogTamagotchi(String name) {
        super(name);
    }

    @Override
    public void play() {
        System.out.println(getName() + " fetches a ball happily!");
        setHappiness(getHappiness() + 20);
        setHunger(getHunger() + 10);
    }
}