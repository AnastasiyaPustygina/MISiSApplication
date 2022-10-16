package com.example.misisapplication.domain;

public class Ingredient {

    private int id;
    private String name;
    private int weight;

    public Ingredient(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name + " "  + weight + " г ";
    }
}
