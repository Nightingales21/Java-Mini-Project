package com.tamagotchi.pets;

import com.tamagotchi.core.Tamagotchi;

public class DogTamagotchi extends Tamagotchi {
    public DogTamagotchi(String name) {
        super(name);
    }

    @Override
    public void play() {
        System.out.println(getName() + " fetches a ball happily!");
    }
}
