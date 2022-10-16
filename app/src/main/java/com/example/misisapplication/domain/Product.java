package com.example.misisapplication.domain;


public class Product {

    private int id;
    private String name;
    private int calories_number;
    private int glycemic_index;
    private int protein_number;
    private int fat_number;
    private int carbohydrate_number;
    private Image image;

    public Product(int id, String name, int calories_number, int glycemic_index,
                        int protein_number, int fat_number,
                                int carbohydrate_number, Image image) {
        this.id = id;
        this.name = name;
        this.calories_number = calories_number;
        this.glycemic_index = glycemic_index;
        this.protein_number = protein_number;
        this.fat_number = fat_number;
        this.carbohydrate_number = carbohydrate_number;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCalories_number() {
        return calories_number;
    }

    public int getGlycemic_index() {
        return glycemic_index;
    }

    public int getProtein_number() {
        return protein_number;
    }

    public int getFat_number() {
        return fat_number;
    }

    public int getCarbohydrate_number() {
        return carbohydrate_number;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories_number=" + calories_number +
                ", glycemic_index=" + glycemic_index +
                ", protein_number=" + protein_number +
                ", fat_number=" + fat_number +
                ", carbohydrate_number=" + carbohydrate_number +
                ", image=" + image +
                '}';
    }
}
