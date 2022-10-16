package com.example.misisapplication.domain.mapper;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.misisapplication.domain.Dish;
import com.example.misisapplication.domain.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class DishMapper {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Dish getFromJsonArray(JSONObject object){
        Dish dish = null;
        try {
            JSONArray ingredientsJson = object.getJSONArray("ingredients");
            IngredientMapper ingredientMapper = new IngredientMapper();
            List<Ingredient> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJson.length(); i++) {
                ingredients.add(ingredientMapper.getFromJsonArray(ingredientsJson.getJSONObject(i)));
            }
            dish = new Dish(object.getInt("id"), object.getString("name"), ingredients,
                    new ImageMapper().getFromJsonArray(object.getJSONObject("image")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dish;
    }
}