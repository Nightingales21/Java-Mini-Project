package com.tamagotchi.pets;
import com.tamagotchi.core.Tamagotchi;
public class CatTamagotchi extends Tamagotchi {
public CatTamagotchi() {
this("Unnamed Cat");
}
public CatTamagotchi(String name) {
super(name);
}
@Override
public void play() {
System.out.println(getName() + " chases a string lazily!");
setHappiness(getHappiness() + 20);
setHunger(getHunger() + 10);
}
}