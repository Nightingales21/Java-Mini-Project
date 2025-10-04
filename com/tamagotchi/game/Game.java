package com.tamagotchi.game;
import com.tamagotchi.core.Player;
import com.tamagotchi.core.Tamagotchi;
import com.tamagotchi.pets.DogTamagotchi;
import com.tamagotchi.pets.CatTamagotchi;
import com.tamagotchi.items.Food;
import com.tamagotchi.items.Toy;
import com.tamagotchi.exceptions.PetDiedException;
import java.util.*;
public class Game {
private Player player;
private Scanner scanner = new Scanner(System.in);
private final String SAVE_FILE = "save.txt";
private Map<String, Food> availableFoods = new HashMap<>();
private Set<Toy> unlockedToys = new HashSet<>();
public void startGame() {
// Populate collections
availableFoods.put("Apple", new Food("Apple", 10));
availableFoods.put("Meat", new Food("Meat", 20));
unlockedToys.add(new Toy("Ball"));
unlockedToys.add(new Toy("String"));
System.out.print("Enter your name: ");
String name = scanner.nextLine();
player = new Player(name);
// ✅ Load progress
player.loadProgress(SAVE_FILE);
boolean running = true;
while (running) {
System.out.println("\n1. Adopt Pet\n2. Show Pets\n3. Interact with Pet\n4. Exit");
int choice = scanner.nextInt();
scanner.nextLine(); // consume newline
switch (choice) {
case 1:
adoptPet();
break;
case 2:
player.showPets();
break;
case 3:
interactWithPet();
break;
case 4:
exitGame();
running = false;
break;
default:
System.out.println("Invalid choice!");
}
// Update pets after each action to simulate time passage
updatePets();
}
}
private void adoptPet() {
System.out.print("Enter pet type (Dog/Cat): ");
String type = scanner.nextLine();
System.out.print("Enter pet name: ");
String pname = scanner.nextLine();
if (type.equalsIgnoreCase("Dog")) {
player.adoptPet(new DogTamagotchi(pname));
} else if (type.equalsIgnoreCase("Cat")) {
player.adoptPet(new CatTamagotchi(pname));
} else {
System.out.println("Unknown pet type!");
}
}
private void interactWithPet() {
List<Tamagotchi> pets = player.getPets();
if (pets.isEmpty()) {
System.out.println("No pets to interact with!");
return;
}
System.out.println("Select pet:");
for (int i = 0; i < pets.size(); i++) {
System.out.println((i + 1) + ". " + pets.get(i).getName());
}
int petIndex = scanner.nextInt() - 1;
scanner.nextLine();
if (petIndex < 0 || petIndex >= pets.size()) {
System.out.println("Invalid pet!");
return;
}
Tamagotchi pet = pets.get(petIndex);
System.out.println("\n1. Feed\n2. Play\n3. Sleep\n4. Bath\n5. Back");
int action = scanner.nextInt();
scanner.nextLine();
try {
switch (action) {
case 1:
System.out.println("Select food: ");
int foodIdx = 1;
for (String foodName : availableFoods.keySet()) {
System.out.println(foodIdx++ + ". " + foodName);
}
int foodChoice = scanner.nextInt() - 1;
scanner.nextLine();
String selectedFood = new ArrayList<>(availableFoods.keySet()).get(foodChoice);
pet.feed(availableFoods.get(selectedFood));
break;
case 2:
System.out.println("Select toy (or 0 for none): ");
int toyIdx = 1;
List<Toy> toyList = new ArrayList<>(unlockedToys);
for (Toy toy : toyList) {
System.out.println(toyIdx++ + ". " + toy.getName());
}
int toyChoice = scanner.nextInt();
scanner.nextLine();
if (toyChoice == 0) {
pet.play();
} else {
Toy selectedToy = toyList.get(toyChoice - 1);
// Assuming an overloaded play(Toy) - added below in Tamagotchi but since abstract, call subclass if needed
pet.play(); // For simplicity, call default and print toy
System.out.println(" using " + selectedToy.getName());
}
break;
case 3:
pet.sleep();
break;
case 4:
pet.bath();
break;
case 5:
return;
default:
System.out.println("Invalid action!");
}
// Check stats after interaction
pet.checkStats();
} catch (PetDiedException e) {
System.out.println(e.getMessage());
pets.remove(pet);
} finally {
// Finally block example: always show status after interaction
if (!pets.isEmpty() && pets.contains(pet)) {
pet.status();
}
}
}
private void updatePets() {
List<Tamagotchi> pets = player.getPets();
for (int i = 0; i < pets.size(); i++) {
Tamagotchi pet = pets.get(i);
pet.update();
try {
pet.checkStats();
} catch (PetDiedException e) {
System.out.println(e.getMessage());
pets.remove(i);
i--; // Adjust index after removal
}
}
}
private void exitGame() {
// ✅ Save before exit
player.saveProgress(SAVE_FILE);
System.out.println("Goodbye!");
}
}