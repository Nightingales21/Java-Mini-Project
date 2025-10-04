package com.tamagotchi.player;

import com.tamagotchi.pet.Pet;
import java.io.*;
import java.util.*;

public class Player {
    private String name;
    private List<Pet> pets = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void adoptPet(Pet pet) {
        pets.add(pet);
        System.out.println(name + " adopted " + pet.getName());
    }

    public void showPets() {
        if (pets.isEmpty()) {
            System.out.println("No pets adopted yet!");
        } else {
            System.out.println("Pets of " + name + ":");
            for (Pet p : pets) {
                System.out.println(" - " + p);
            }
        }
    }

    // ✅ Save pets to file
    public void saveProgress(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Pet p : pets) {
                // Format: type,name,hunger,happiness
                writer.write(p.getClass().getSimpleName() + "," +
                             p.getName() + "," +
                             p.getHunger() + "," +
                             p.getHappiness());
                writer.newLine();
            }
            System.out.println("Progress saved!");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    // ✅ Load pets from file
    public void loadProgress(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No previous save found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String type = data[0];
                    String pname = data[1];
                    int hunger = Integer.parseInt(data[2]);
                    int happiness = Integer.parseInt(data[3]);

                    Pet pet = null;
                    if (type.equals("Dog")) {
                        pet = new com.tamagotchi.pet.Dog(pname);
                    } else if (type.equals("Cat")) {
                        pet = new com.tamagotchi.pet.Cat(pname);
                    }

                    if (pet != null) {
                        pet.setHunger(hunger);
                        pet.setHappiness(happiness);
                        pets.add(pet);
                    }
                }
            }
            System.out.println("Progress loaded!");
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}
