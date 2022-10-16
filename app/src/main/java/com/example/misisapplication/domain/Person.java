package com.example.misisapplication.domain;

import java.util.List;

public class Person {

    private int id;
    private List<Recipe> favorite;


    public Person(int id, List<Recipe> favorite) {
        this.id = id;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public List<Recipe> getFavorite() {
        return favorite;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", favorite=" + favorite +
                '}';
    }
}
