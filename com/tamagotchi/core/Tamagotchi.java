package com.tamagotchi.core;

public abstract class Tamagotchi {
    private String name;
    private int age;
    private int hunger;
    private int happiness;
    private int health;

    // Constructor chaining
    public Tamagotchi() {
        this("Unnamed");
    }

    public Tamagotchi(String name) {
        this.name = name;
        this.age = 0;
        this.hunger = 50;
        this.happiness = 50;
        this.health = 100;
    }

    // Encapsulation (getters & setters with validation)
    public String getName() { return name; }

    public void setHunger(int hunger) {
        if (hunger < 0) this.hunger = 0;
        else if (hunger > 100) this.hunger = 100;
        else this.hunger = hunger;
    }

    public int getHunger() { return hunger; }

    // Common method
    public void feed() {
        setHunger(hunger - 10);
        System.out.println(name + " was fed. Hunger: " + hunger);
    }

    // Abstract method (to be implemented by subclasses)
    public abstract void play();

    // Status method
    public void status() {
        System.out.println(name + " (Age: " + age + ") | Hunger: " + hunger + " | Happiness: " + happiness + " | Health: " + health);
    }
}
