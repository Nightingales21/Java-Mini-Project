package com.tamagotchi.ui;

public class AsciiArt {

    public static void displayDog() {
        String dog =
            "|\\_/|\n" +
            "| @ @   Woof!\n" +
            "|   <>              _\n" +
            "|  _/\\------____ ((| |))\n" +
            "|               `--' |\n" +
            "|                     |\n" +
            "|    |       |    |   |\n" +
            "|____|_______|____|___|\n";
        System.out.println(dog);
    }

    public static void displayCat() {
        String cat =
            " /\\_/\\  \n" +
            "( o.o )  Meow!\n" +
            " > ^ <\n";
        System.out.println(cat);
    }

    public static void displayPet(String petType) {
        if (petType == null) {
            System.out.println("No pet type specified!");
            return;
        }
        switch (petType.toLowerCase()) {
            case "dog":
                displayDog();
                break;
            case "cat":
                displayCat();
                break;
            default:
                System.out.println("Unknown pet type. Please choose 'dog' or 'cat'.");
                break;
        }
    }
}
