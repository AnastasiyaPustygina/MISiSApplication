package com.example.misisapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misisapplication.DbImitation;
import com.example.misisapplication.R;
import com.example.misisapplication.adapters.AllDishFromCategoryAdapter;
import com.example.misisapplication.adapters.DishesRecyclerAdapter;
import com.example.misisapplication.domain.Dish;
import com.example.misisapplication.domain.Image;
import com.example.misisapplication.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;


public class AllDishFromCategoryFragment extends Fragment {

    private List<Dish> dishList = new ArrayList<>();
    private static RecyclerView recyclerView;
    private static AllDishFromCategoryAdapter allDishFromCategoryAdapter;
    private static AllDishFromCategoryFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dishList = DbImitation.getDishes();
        fragment = AllDishFromCategoryFragment.this;
       View view = inflater.inflate(R.layout.fragment_all_dish_from_category, container, false);
        recyclerView =view.findViewById(R.id.rv_all_dish_from_category);
        TextView tv_nameOfCategory = view.findViewById(R.id.tv_nameOfCategory);
        tv_nameOfCategory.setText(getArguments().getString("name"));
        allDishFromCategoryAdapter = new AllDishFromCategoryAdapter(view.getContext(), dishList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(allDishFromCategoryAdapter);
        return view;
    }
    public static void update(){
        recyclerView.setAdapter(new AllDishFromCategoryAdapter(recyclerView.getContext(), DbImitation.getDishes()));
    }
}