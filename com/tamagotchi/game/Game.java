package com.tamagotchi.game;

import com.tamagotchi.player.Player;
import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private final String SAVE_FILE = "save.txt";

    public void start() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        player = new Player(name);

        // ✅ Load progress
        player.loadProgress(SAVE_FILE);

        boolean running = true;
        while (running) {
            System.out.println("\n1. Adopt Pet\n2. Show Pets\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch(choice) {
                case 1:
                    adoptPet();
                    break;
                case 2:
                    player.showPets();
                    break;
                case 3:
                    exitGame();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void adoptPet() {
        System.out.print("Enter pet type (Dog/Cat): ");
        String type = scanner.nextLine();
        System.out.print("Enter pet name: ");
        String pname = scanner.nextLine();

        if (type.equalsIgnoreCase("Dog")) {
            player.adoptPet(new com.tamagotchi.pet.Dog(pname));
        } else if (type.equalsIgnoreCase("Cat")) {
            player.adoptPet(new com.tamagotchi.pet.Cat(pname));
        } else {
            System.out.println("Unknown pet type!");
        }
    }

    private void exitGame() {
        // ✅ Save before exit
        player.saveProgress(SAVE_FILE);
        System.out.println("Goodbye!");
    }
}
