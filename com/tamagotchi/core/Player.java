package com.tamagotchi.core;

import com.tamagotchi.pets.DogTamagotchi;
import com.tamagotchi.pets.CatTamagotchi;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors; // Add this import

public class Player {
    private String name;
    private List<Tamagotchi> pets = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void adoptPet(Tamagotchi pet) {
        pets.add(pet);
        System.out.println(name + " adopted " + pet.getName());
    }

    public void showPets() {
        if (pets.isEmpty()) {
            System.out.println("No pets adopted yet!");
        } else {
            System.out.println("Pets of " + name + ":");
            // Sorting by hunger using lambda, fixed for pre-Java 16
            List<Tamagotchi> sortedPets = pets.stream()
                                              .sorted(Comparator.comparingInt(Tamagotchi::getHunger))
                                              .collect(Collectors.toList()); // Changed .toList() to .collect(Collectors.toList())
            for (Tamagotchi p : sortedPets) {
                System.out.println(" - " + p);
            }
        }
    }

    public List<Tamagotchi> getPets() {
        return pets;
    }

    // ✅ Save pets to file (multi-user support in single file)
    public void saveProgress(String filename) {
        File file = new File(filename);
        List<String> otherLines = new ArrayList<>();
        String myHeader = "Player:" + name;
        boolean inMySection = false;
        boolean foundMySection = false;

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Player:")) {
                        if (line.equals(myHeader)) {
                            inMySection = true;
                            foundMySection = true;
                        } else {
                            inMySection = false;
                            otherLines.add(line);
                        }
                    } else {
                        if (!inMySection) {
                            otherLines.add(line);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading for save: " + e.getMessage());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write other players' data
            for (String line : otherLines) {
                writer.write(line);
                writer.newLine();
            }

            // Write my header and pets
            writer.write(myHeader);
            writer.newLine();
            for (Tamagotchi p : pets) {
                // Format: type,name,hunger,happiness,health
                writer.write(p.getClass().getSimpleName() + "," +
                             p.getName() + "," +
                             p.getHunger() + "," +
                             p.getHappiness() + "," +
                             p.getHealth());
                writer.newLine();
            }
            System.out.println("Progress saved for " + name + "!");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    // ✅ Load pets from file (multi-user support)
    public void loadProgress(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No previous save found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean mySection = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Player:")) {
                    if (line.equals("Player:" + name)) {
                        mySection = true;
                    } else {
                        mySection = false;
                    }
                    continue;
                }
                if (mySection) {
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        String type = data[0];
                        String pname = data[1];
                        int hunger = Integer.parseInt(data[2]);
                        int happiness = Integer.parseInt(data[3]);
                        int health = Integer.parseInt(data[4]);

                        Tamagotchi pet = null;
                        if (type.equals("DogTamagotchi")) {
                            pet = new DogTamagotchi(pname);
                        } else if (type.equals("CatTamagotchi")) {
                            pet = new CatTamagotchi(pname);
                        }

                        if (pet != null) {
                            pet.setHunger(hunger);
                            pet.setHappiness(happiness);
                            pet.setHealth(health);
                            pets.add(pet);
                        }
                    }
                }
            }
            System.out.println("Progress loaded for " + name + "!");
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}