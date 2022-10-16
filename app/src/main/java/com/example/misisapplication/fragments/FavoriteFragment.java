package com.example.misisapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misisapplication.R;
import com.example.misisapplication.adapters.FavoriteAdapter;
import com.example.misisapplication.domain.Dish;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {
    private List<Dish> dishes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_favorites_dishes);
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(view.getContext(), dishes);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(favoriteAdapter);



        return view;
    }
}