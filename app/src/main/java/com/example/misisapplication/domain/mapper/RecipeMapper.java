package com.example.misisapplication.domain.mapper;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.misisapplication.domain.Dish;
import com.example.misisapplication.domain.Recipe;

import org.json.JSONException;
import org.json.JSONObject;

public class RecipeMapper {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Recipe getFromJsonArray(JSONObject object){
        Recipe recipe = null;
        try {
            Dish dish = new DishMapper().getFromJsonArray(object.getJSONObject("dish"));
            recipe = new Recipe(object.getInt("id"), object.getString("text"), dish);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipe;
    }
}