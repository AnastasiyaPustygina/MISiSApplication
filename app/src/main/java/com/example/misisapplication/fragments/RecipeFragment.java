package com.example.misisapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.misisapplication.DbImitation;
import com.example.misisapplication.R;
import com.example.misisapplication.domain.Dish;
import com.example.misisapplication.domain.Image;
import com.example.misisapplication.domain.Ingredient;
import com.example.misisapplication.domain.Recipe;
import com.example.misisapplication.rest.AppApi;
import com.example.misisapplication.rest.AppApiVolley;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class RecipeFragment extends Fragment {

    private static TextView tv_textRecipe, tv_ingredients;
    private static ImageView iv_recipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppApiVolley volley = new AppApiVolley(getContext());
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        tv_textRecipe = view.findViewById(R.id.recipe_txt);
        tv_ingredients = view.findViewById(R.id.recipe_ingredients_names);
        iv_recipe = view.findViewById(R.id.dish_image_recipe);
        volley.findRecipeByDishId(getArguments().getInt("id"));

        return view;
    }
    public static void fillRecipe(Recipe recipe) {
        final StringBuilder s = new StringBuilder();
        recipe.getDish().getIngredients().forEach((i) -> s.append(i.toString() + "\n"));
        tv_ingredients.setText(s);
        tv_textRecipe.setText(recipe.getTxt());
        try {
            Picasso.with(tv_textRecipe.getContext()).load(recipe.getDish().getImage().getPath()).into(iv_recipe);
        } catch (Exception e){
            Log.e("ImageException", e.getMessage());
        }
    }
}