package com.tamagotchi.core;

public class Box<T> {
    private T item;

    public void setItem(T item) { this.item = item; }
    public T getItem() { return item; }

    // Generic method
    public <E> void printData(E data) {
        System.out.println("Data: " + data);
    }
}
