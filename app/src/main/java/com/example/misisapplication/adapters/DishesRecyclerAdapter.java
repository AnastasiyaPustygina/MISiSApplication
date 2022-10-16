package com.example.misisapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import androidx.navigation.fragment.NavHostFragment;
import com.example.misisapplication.fragments.SearchFragment;
import com.example.misisapplication.R;
import com.example.misisapplication.domain.Dish;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DishesRecyclerAdapter extends RecyclerView.Adapter<DishesRecyclerAdapter.ViewHolderDishes> {

    private Context context;
    private LayoutInflater inflater;
    private List<Dish> dishes;
    private SearchFragment fragment;

    public DishesRecyclerAdapter(Context context, List<Dish> dishes, SearchFragment fragment) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.dishes = dishes;
        this.fragment = fragment;
    }

     @NonNull
     @Override
     public ViewHolderDishes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         View view = LayoutInflater.from(context).inflate(R.layout.custom_child_rv_item, parent, false);

         return new ViewHolderDishes(view);
     }

     @Override
     public void onBindViewHolder(@NonNull ViewHolderDishes holder, int position) {
         try {
             Picasso.with(context).load(dishes.get(position).getImage().getPath()).into(holder.dish_image);
         } catch (Exception e){
             Log.e("ImageException", e.getMessage());
         }

         holder.dish_name.setText(dishes.get(position).getName());

         holder.dish_image.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Bundle bundle = new Bundle();
                 bundle.putInt("id", dishes.get(position).getId());
                 holder.dish_image.setOnClickListener((view1) -> {
                     NavHostFragment.
                             findNavController(fragment).navigate(
                                     R.id.action_searchFragment_to_recipeFragment, bundle);
                 });
                 holder.dish_image.performClick();
             }
         });

     }

     @Override
     public int getItemCount() {
         return dishes.size();
     }


     public static class ViewHolderDishes extends RecyclerView.ViewHolder {

        ImageView dish_image;
        TextView dish_name;

        public ViewHolderDishes(@NonNull View itemView) {
            super(itemView);

            dish_image = itemView.findViewById(R.id.dish_image_button);
            dish_name = itemView.findViewById(R.id.dish_name);

        }

    }
}
