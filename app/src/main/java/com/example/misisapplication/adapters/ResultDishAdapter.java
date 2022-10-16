package com.example.misisapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.navigation.fragment.NavHostFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misisapplication.R;
import com.example.misisapplication.domain.Dish;
import com.example.misisapplication.fragments.ResultDishFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ResultDishAdapter extends RecyclerView.Adapter<ResultDishAdapter.ViewHolder> {

    private Context context;
    private List<Dish> dishes = new ArrayList<>();
    private LayoutInflater inflater;
    private ResultDishFragment fragment;

    public ResultDishAdapter(Context context, List<Dish> dishes, ResultDishFragment fragment) {
        this.context = context;
        this.dishes = dishes;
        this.inflater = LayoutInflater.from(context);
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ResultDishAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_item_rv_found_dish, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultDishAdapter.ViewHolder holder, int position) {
        holder.item_dish_name.setText(dishes.get(position).getName());
        final StringBuilder s = new StringBuilder();
        dishes.get(position).getIngredients().forEach((i) -> s.append(i.toString() + "\n"));
        holder.ingredients_txt.setText(s.toString());
        try {
            Picasso.with(context).load(dishes.get(position).getImage().getPath()).into(holder.found_dish_image);
        } catch (Exception e){
            Log.e("ImageException", e.getMessage());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", dishes.get(position).getId());
                holder.itemView.setOnClickListener((view1) -> {
                    NavHostFragment.
                            findNavController(fragment).navigate(
                                    R.id.action_resultDishFragment_to_recipeFragment, bundle);
                });
                holder.itemView.performClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView found_dish_image;
        ImageButton favorite_food_btn;
        TextView item_dish_name, ingredients_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            found_dish_image = itemView.findViewById(R.id.found_dish_image);
            favorite_food_btn = itemView.findViewById(R.id.favorite_dish_btn);
            item_dish_name = itemView.findViewById(R.id.item_dish_name);
            ingredients_txt = itemView.findViewById(R.id.ingredients_txt);
        }
    }

}
