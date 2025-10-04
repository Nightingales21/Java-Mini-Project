package com.tamagotchi.core;
import com.tamagotchi.exceptions.PetDiedException;
import com.tamagotchi.items.Food;
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
public void setHappiness(int happiness) {
if (happiness < 0) this.happiness = 0;
else if (happiness > 100) this.happiness = 100;
else this.happiness = happiness;
}
public int getHappiness() { return happiness; }
public void setHealth(int health) {
if (health < 0) this.health = 0;
else if (health > 100) this.health = 100;
else this.health = health;
}
public int getHealth() { return health; }
public int getAge() { return age; }
// Common method (default feed)
public void feed() {
setHunger(hunger - 10);
System.out.println(name + " was fed. Hunger: " + hunger);
}
// Overloaded feed (compile-time polymorphism)
public void feed(Food food) {
setHunger(getHunger() - food.getNutrition());
System.out.println(name + " ate " + food.getName() + ". Hunger: " + getHunger());
}
// Overloaded feed with string (compile-time polymorphism)
public void feed(String foodName) {
// Assuming a default nutrition for unnamed food; in practice, this could lookup from a map
setHunger(getHunger() - 5);
System.out.println(name + " ate " + foodName + ". Hunger: " + getHunger());
}
// Abstract method (to be implemented by subclasses)
public abstract void play();
// New functions
public void sleep() {
setHunger(getHunger() + 5); // Gets a bit hungry while sleeping
setHappiness(getHappiness() + 10);
setHealth(getHealth() + 10);
System.out.println(name + " slept. Happiness and health improved!");
}
public void bath() {
setHappiness(getHappiness() + 5);
setHealth(getHealth() + 20);
System.out.println(name + " took a bath. Health improved!");
}
// Update stats over time
public void update() {
setHunger(getHunger() + 5);
if (getHunger() > 80) {
setHealth(getHealth() - 5);
}
if (getHappiness() < 20) {
setHealth(getHealth() - 5);
}
}
// Check if pet died
public void checkStats() throws PetDiedException {
if (health <= 0) {
throw new PetDiedException(name + " has died due to poor health!");
}
}
// Status method
public void status() {
System.out.println(name + " (Age: " + age + ") | Hunger: " + hunger + " | Happiness: " + happiness + " | Health: " + health);
}
@Override
public String toString() {
return name + " (Hunger: " + hunger + ", Happiness: " + happiness + ", Health: " + health + ")";
}
}