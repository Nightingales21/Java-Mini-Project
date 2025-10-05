# Tamagotchi Emulator Code Structure

## To run the emulator  
In the repo folder, run `javac com/tamagotchi/**/*.java`  
Start the game with `java com.tamagotchi.game.Main`

## com/tamagotchi/core/
    ┗Tamagotchi.java
    ┗Player.java
    ┗Box.java   (Generic class)

## com/tamagotchi/pets/
    ┗DogTamagotchi.java
    ┗CatTamagotchi.java

## com/tamagotchi/game/
    ┗Game.java
    ┗Main.java

## com/tamagotchi/items/
    ┗Food.java
    ┗Toy.java

## com/tamagotchi/exceptions/
    ┗PetDiedException.java

# Feature Mapping to OOP Principles

## 1. Classes & Objects

* **Classes:**

  * `Tamagotchi` (base class for pets)
  * `DogTamagotchi`, `CatTamagotchi` (derived classes)
  * `Player` (represents the user)
  * `Game` (game engine)
  * `Food`, `Toy` (items)
* **Objects created in:** `Main.java` (`Game` object starts, `Player` adopts `Tamagotchi` pets).

---

## 2. **Encapsulation**

* All attributes in `Tamagotchi` are **private** (`name`, `hunger`, `happiness`, `health`).
* Access controlled through **getters and setters**.
* **Validation logic:**

  ```java
  public void setHunger(int hunger) {
      if (hunger < 0) this.hunger = 0;
      else if (hunger > 100) this.hunger = 100;
      else this.hunger = hunger;
  }
  ```

---

## 3. **Inheritance**

* `Tamagotchi` → base class.
* `DogTamagotchi` and `CatTamagotchi` → derived classes.
* `super` keyword used in subclass constructors to call the parent constructor.

---

## 4. **Polymorphism**

* **Compile-time polymorphism (overloading):**
  `feed(Food food)` and `feed(String foodName)` methods.
* **Runtime polymorphism (overriding):**

  * `DogTamagotchi.play()` → “fetches a ball”
  * `CatTamagotchi.play()` → “chases a string”
* **Dynamic method dispatch:**

  ```java
  Tamagotchi pet = new DogTamagotchi("Buddy");
  pet.play();  // Dog’s version is called
  ```

---

## 5. **Abstraction**

* `Tamagotchi` is an **abstract class** with `public abstract void play();`.
* Subclasses (`DogTamagotchi`, `CatTamagotchi`) implement it differently.

---

## 6. **Constructors**

* **Default constructor:** assigns default pet values.
* **Parameterized constructor:** allows naming the pet.
* **Constructor overloading & chaining:**

  ```java
  public DogTamagotchi() { this("Unnamed Dog"); }
  public DogTamagotchi(String name) { super(name); }
  ```

---

## 7. **Access Modifiers**

* `private` → attributes (`hunger`, `health`) in `Tamagotchi`.
* `protected` → helper methods in `Tamagotchi` (e.g., internal stat updates).
* `public` → API methods like `feed()`, `play()`, `status()`.
* *default* → utility/helper classes inside the same package.

---

## 8. **Packages**

* `com.tamagotchi.core` → `Tamagotchi`, `Player`, `Box<T>`.
* `com.tamagotchi.pets` → `DogTamagotchi`, `CatTamagotchi`.
* `com.tamagotchi.items` → `Food`, `Toy`.
* `com.tamagotchi.game` → `Game`, `Main`.
* `com.tamagotchi.exceptions` → `PetDiedException`.

---

## 9. **Exception Handling**

* **Custom exception:** `PetDiedException`.
* Thrown when `health <= 0`.
* **try–catch–finally:** used in `Game.java` when interacting with pets.

---

## 10. **Generics**

* `Box<T>` generic class for storing items (`Food`, `Toy`).
* **Generic method:**

  ```java
  public <T> void printData(T data) {
      System.out.println(data);
  }
  ```
* **Bounded generic:** used for numeric stats (`<T extends Number>`).

---

## 11. **Collections Framework**

* `ArrayList<Tamagotchi>` → stores player’s pets.
* `HashMap<String, Food>` → stores available foods with names as keys.
* `HashSet<String>` → stores unlocked toys (unique items).
* **Iteration:** enhanced for-loop in `Player.showPets()`.
* **Sorting/searching:** pets can be sorted by hunger or happiness using **lambda expressions**.

---

## 12. **File Handling**

* **Saving game progress:** `Player.saveProgress()` writes pet stats to `save.txt`.
* **Loading progress:** `Game.java` can read data back using `Scanner`/`BufferedReader`.

---

## Summary

Every major Java OOP feature is demonstrated in this project:

* **Encapsulation:** private fields + getters/setters
* **Inheritance:** base/derived pet classes
* **Polymorphism:** overloading + overriding with dynamic dispatch
* **Abstraction:** abstract class with abstract method
* **Constructors:** default, parameterized, chaining
* **Access Modifiers:** all four used
* **Packages:** project split into logical modules
* **Exception Handling:** custom exception + try–catch–finally
* **Generics:** generic class + method
* **Collections:** List, Set, Map with iteration & lambdas
* **File Handling:** saving/loading pets
