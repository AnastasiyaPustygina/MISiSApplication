package com.example.misisapplication.domain.mapper;

import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.misisapplication.domain.Ingredient;
import org.json.JSONException;
import org.json.JSONObject;
public class IngredientMapper {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Ingredient getFromJsonArray(JSONObject object){
        Ingredient ingredient = null;
        try {
            ingredient = new Ingredient(object.getInt("id"),
                    object.getString("name"),
                    object.getInt("weight"));
        } catch (JSONException e) {
            Log.e("IngredientMapper", e.getMessage());
        }
        return ingredient;
    }
}