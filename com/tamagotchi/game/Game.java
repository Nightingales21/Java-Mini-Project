package com.tamagotchi.game;

import com.tamagotchi.core.*;
import com.tamagotchi.pets.*;
import java.util.*;

public class Game {
    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public void startGame() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        player = new Player(username);

        while (running) {
            menu();
        }
    }

    private void menu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Adopt Pet");
        System.out.println("2. Show Pets");
        System.out.println("3. Save & Exit");
        System.out.print("Choose: ");
        int choice = scanner.nextInt(); scanner.nextLine();

        switch(choice) {
    case 1:
        adoptPet();
        break;
    case 2:
        player.showPets();
        break;
    case 3:
        exitGame();
        break;
    default:
        System.out.println("Invalid choice, try again!");
    }

    }

    private void adoptPet() {
        System.out.print("Enter pet type (dog/cat): ");
        String type = scanner.nextLine();
        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();

        Tamagotchi pet;
        if (type.equalsIgnoreCase("dog")) {
            pet = new DogTamagotchi(name);
        } else {
            pet = new CatTamagotchi(name);
        }

        player.adoptPet(pet);
    }

    private void exitGame() {
        player.saveProgress();
        running = false;
        System.out.println("Game saved. Goodbye!");
    }
}
