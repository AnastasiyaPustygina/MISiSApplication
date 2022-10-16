package com.example.misisapplication.rest;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misisapplication.domain.Category;
import com.example.misisapplication.domain.Dish;
import com.example.misisapplication.domain.Person;
import com.example.misisapplication.domain.Product;
import com.example.misisapplication.domain.Recipe;

import java.util.List;

public interface AppApi {
    void findAllCategories();

    void findDishesByCategoryId(int id);

    void findDishesByCategoryIds(List<Integer> ids);

    void findTenDishesByCategoryId(int id);

    void findDishById(int id);

    void insertPerson();

    void findProductByIngredientId(int id);

    void findRecipeByDishId(int id);

    void findRecipeByPersonId(int id);

    void deleteRecipeFromFavouritesByPersonIdAndRecipeId(int personId, int recipeId);

    void addRecipeToFavouritesByPersonIdAndRecipeId(int personId, int recipeId);
}