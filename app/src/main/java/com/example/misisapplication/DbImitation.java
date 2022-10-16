package com.example.misisapplication;

import com.example.misisapplication.domain.Category;
import com.example.misisapplication.domain.Dish;
import com.example.misisapplication.domain.Ingredient;
import com.example.misisapplication.domain.Product;
import com.example.misisapplication.domain.Recipe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbImitation {
    private static final List<Category> categories = new ArrayList<>();
    private static final List<Dish> dishes = new ArrayList<>();
    private static final List<Ingredient> ingredients = new ArrayList<>();
    private static final List<Recipe> favouriteRecipes = new ArrayList<>();
    private static final Map<Integer, List<Dish>> dishCategoryMap = new HashMap<Integer, List<Dish>>();
    private static Product currentProduct;
    private static Recipe currentRecipe;
    private static Dish currentDish;
    private static int personId;
    public static void addValueMap(Integer id, List<Dish> dishes){
        dishCategoryMap.put(id, dishes);
    }
    public static void setPersonId(int id){
        personId = id;
    }
    public static int getPersonId() {
        return personId;
    }
    public static Map<Integer, List<Dish>> getValueMap() {
        return dishCategoryMap;
    }
    public static Recipe getCurrentRecipe() {
        return currentRecipe;
    }
    public static void setCurrentRecipe(Recipe recipe) {
        currentRecipe = recipe;
    }
    public static Product getCurrentProduct() {
        return currentProduct;
    }
    public static void setCurrentProduct(Product product) {
        currentProduct = product;
    }
    public static Dish getCurrentDish() {
        return currentDish;
    }
    public static void setCurrentDish(Dish dish) {
        currentDish = dish;
    }
    //Добавление в листы
    public static void addCategory(Category category) {
        categories.add(category);
    }
    public static void addDish(Dish dish) {
        dishes.add(dish);
    }
    public static void addIngredients(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    public static void addRecipe(Recipe recipe) {
        favouriteRecipes.add(recipe);
    }
    //Взять информацию из листов
    public static List<Category> getCategories() {
        return categories;
    }
    public static List<Dish> getDishes() {
        return dishes;
    }
    public static List<Ingredient> getIngredients() {
        return ingredients;
    }
    public static List<Recipe> getRecipes() {
        return favouriteRecipes;
    }
    //Очистить листы
    public static void clearCategoryList() {
        categories.clear();
    }
    public static void clearDishList() {
        dishes.clear();
    }
    public static void clearIngredientList() {
        ingredients.clear();
    }
    public static void clearRecipeList() {
        favouriteRecipes.clear();
    }
    public static void clearMap() {
        dishCategoryMap.clear();
    }

    public static void removeFromRecipes(Recipe recipe){
        favouriteRecipes.remove(recipe);
    }

}