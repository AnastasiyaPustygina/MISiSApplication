package com.example.misisapplication.domain;

import java.util.List;

public class Dish {

    private int id;
    private String name;
    private List<Ingredient> ingredients;
    private Image image;

    public Dish(int id, String name, List<Ingredient> ingredients, Image image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", image_id=" + image +
                '}';
    }
}
