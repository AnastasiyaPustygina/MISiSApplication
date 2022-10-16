package com.example.misisapplication.adapters;

import android.content.Context;
import android.util.Log;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoundDishAdapter extends RecyclerView.Adapter<FoundDishAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<Dish> found_dishes;

    public FoundDishAdapter(Context context, List<Dish> found_dishes) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.found_dishes = found_dishes;
    }

    @NonNull
    @Override
    public FoundDishAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_main_rv_itrem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoundDishAdapter.ViewHolder holder, int position) {
        Dish dish = found_dishes.get(position);
        final StringBuilder s = new StringBuilder();
        dish.getIngredients().forEach((i) -> s.append(i.toString()));
        holder.ingredients_txt.setText(s.toString());
        holder.item_dish_name.setText(dish.getName());
        try {
            Picasso.with(context).load(found_dishes.get(position).getImage().getPath()).into(holder.found_dish_image);
        } catch (Exception e){
            Log.e("ImageException", e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return found_dishes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView found_dish_image;
        TextView item_dish_name, ingredients_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            found_dish_image = itemView.findViewById(R.id.found_dish_image);
            item_dish_name = itemView.findViewById(R.id.item_dish_name);
            ingredients_txt = itemView.findViewById(R.id.ingredients_txt);
        }
    }
}
