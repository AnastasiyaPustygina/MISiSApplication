package com.example.misisapplication.domain;

import java.util.List;

public class Recipe {

    private int id;
    private String txt;
    private Dish dish;


    public Recipe(int id, String txt, Dish dish) {
        this.id = id;
        this.txt = txt;
        this.dish = dish;
    }

    public int getId() {
        return id;
    }

    public String getTxt() {
        return txt;
    }

    public Dish getDish() {
        return dish;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", txt='" + txt + '\'' +
                ", dish=" + dish ;
    }
}
