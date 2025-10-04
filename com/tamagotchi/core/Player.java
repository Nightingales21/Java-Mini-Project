package com.tamagotchi.core;

import java.util.*;
import java.io.*;

public class Player {
    private String username;
    private List<Tamagotchi> pets;

    public Player(String username) {
        this.username = username;
        this.pets = new ArrayList<>();
    }

    public void adoptPet(Tamagotchi pet) {
        pets.add(pet);
        System.out.println(username + " adopted " + pet.getName());
    }

    public void showPets() {
        for (Tamagotchi pet : pets) {
            pet.status();
        }
    }

    // File handling (save progress)
    public void saveProgress() {
        try (FileWriter fw = new FileWriter("save.txt")) {
            for (Tamagotchi pet : pets) {
                fw.write(pet.getName() + "," + pet.getHunger() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
