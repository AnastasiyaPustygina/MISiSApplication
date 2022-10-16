package com.example.misisapplication.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misisapplication.R;
import com.example.misisapplication.domain.Dish;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Context context;
    private List<Dish> dishes = new ArrayList<>();
    private LayoutInflater inflater;

    public FavoriteAdapter(Context context, List<Dish> dishes) {
        this.context = context;
        this.dishes = dishes;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_favorite_food, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {

        //ТРЕБУЕТСЯ ЗАПОЛНИТЬ

    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView favorite_food_image;
        TextView item_favorite_name, ingredients_txt_food_item;
        ImageButton favorite_dish_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            favorite_food_image = itemView.findViewById(R.id.favorite_food_image);
            item_favorite_name = itemView.findViewById(R.id.item_favorite_name);
            ingredients_txt_food_item = itemView.findViewById(R.id.ingredients_txt_food_item);
            favorite_dish_btn = itemView.findViewById(R.id.favorite_dish_btn);

        }
    }

}
