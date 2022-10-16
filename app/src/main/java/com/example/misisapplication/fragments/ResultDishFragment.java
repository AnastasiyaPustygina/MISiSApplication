package com.example.misisapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageButton;
import android.util.Log;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misisapplication.DbImitation;
import com.example.misisapplication.R;
import com.example.misisapplication.adapters.FavoriteAdapter;
import com.example.misisapplication.adapters.ResultDishAdapter;
import com.example.misisapplication.domain.Dish;
import com.example.misisapplication.domain.Image;
import com.example.misisapplication.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class ResultDishFragment extends Fragment {

    List<Dish> dishList = new ArrayList<>();
    private static RecyclerView recyclerView;
    private static ResultDishAdapter resultDishAdapter;
    private ImageButton backStack;
    private static ResultDishFragment resultingDishFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_dish, container, false);
        resultingDishFragment = ResultDishFragment.this;
        recyclerView = view.findViewById(R.id.rv_found);
        resultDishAdapter = new ResultDishAdapter(view.getContext(), dishList, ResultDishFragment.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(resultDishAdapter);
        backStack = view.findViewById(R.id.back_space_button_found);
        backStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backStack.setOnClickListener((view1) -> {
                    NavHostFragment.
                            findNavController(ResultDishFragment.this).navigate(
                                    R.id.action_resultDishFragment_to_searchFragment);
                });
                backStack.performClick();
            }
        });

        return view;
    }
    public static void update(){
        Log.e("ups", DbImitation.getDishes().toString());
        resultDishAdapter = new ResultDishAdapter(recyclerView.getContext(), DbImitation.getDishes(), resultingDishFragment);
        recyclerView.setAdapter(resultDishAdapter);
    }
}