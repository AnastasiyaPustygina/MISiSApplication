package com.example.misisapplication.domain.mapper;

import android.util.Log;

import com.example.misisapplication.domain.Category;

import org.json.JSONException;
import org.json.JSONObject;

public class CategoryMapper {
    public Category getFromJsonArray(JSONObject object) {
        Category category = null;
        try {
            category = new Category(object.getInt("id"), object.getString("name"));
        } catch (JSONException e) {
            Log.e("CategoryMapper", e.getMessage());
        }
        return category;
    }
}