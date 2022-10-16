package com.example.misisapplication.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.navigation.fragment.NavHostFragment;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misisapplication.DbImitation;
import com.example.misisapplication.MainActivity;
import com.example.misisapplication.R;
import com.example.misisapplication.adapters.CategoryAdapter;
import com.example.misisapplication.adapters.DishesRecyclerAdapter;
import com.example.misisapplication.domain.Category;
import com.example.misisapplication.domain.Dish;
import com.example.misisapplication.domain.Image;
import com.example.misisapplication.rest.AppApiVolley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchFragment extends Fragment {

    private List<Category> categories = new ArrayList<>();
    private ArrayList<Integer> ids = new ArrayList<>();
    private static SearchFragment searchFragment;
    private static CategoryAdapter categoryAdapter;
    private static RecyclerView rvCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DbImitation.clearDishList();
        DbImitation.clearMap();
        DbImitation.clearCategoryList();
        searchFragment = SearchFragment.this;
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        rvCategory = view.findViewById(R.id.rv_category);
        AppApiVolley volley = new AppApiVolley(getContext());
        volley.findAllCategories();
        categoryAdapter = new CategoryAdapter(getContext(), categories, SearchFragment.this);
        rvCategory.setAdapter(categoryAdapter);
        rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryAdapter.notifyDataSetChanged();
        AppCompatButton salad = view.findViewById(R.id.choose_salad);
        AppCompatButton soup = view.findViewById(R.id.choose_soup);
        AppCompatButton secondFood = view.findViewById(R.id.choose_second);
        AppCompatButton pp = view.findViewById(R.id.choose_pp);
        AppCompatButton post = view.findViewById(R.id.choose_post);
        AppCompatButton bakery = view.findViewById(R.id.choose_bakery);
        AppCompatButton vegan = view.findViewById(R.id.choose_vegan);
        AppCompatButton dessert = view.findViewById(R.id.choose_dessert);
        AppCompatButton veganskoe = view.findViewById(R.id.choose_veganskoe);
        AppCompatButton drink = view.findViewById(R.id.choose_drink);

        AppCompatButton searchButtonMainFragment = view.findViewById(R.id.search_button_main_fragment);

        searchButtonMainFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volley.findDishesByCategoryIds(ids);
                searchButtonMainFragment.setOnClickListener((view1) -> {
                    NavHostFragment.
                            findNavController(SearchFragment.this).navigate(
                                    R.id.action_searchFragment_to_resultDishFragment);
                });
                searchButtonMainFragment.performClick();
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categories = DbImitation.getCategories();
                AppCompatButton button = (AppCompatButton) view;
                Drawable background =
                        button.getBackground();
                if (background.getConstantState().equals(getResources()
                        .getDrawable(R.drawable.custom_btn_v2_orange).getConstantState())) {
                    ids.remove((Object) categories.stream().filter(c -> c.getName().equals(button.getText())).findAny().get().getId());
                    button.setTextColor(getResources().getColor(R.color.black));
                    button.setBackgroundDrawable(getResources()
                            .getDrawable(R.drawable.custom_choose_button));
                } else {
                    Log.e(button.getText().toString() + "", categories.toString());
                    ids.add(categories.stream().filter(c -> c.getName().equals(button.getText().toString())).findAny().get().getId());
                    button.setTextColor(getResources().getColor(R.color.white));
                    button.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_btn_v2_orange));
                }

            }
        };

        salad.setOnClickListener(listener);
        soup.setOnClickListener(listener);
        secondFood.setOnClickListener(listener);
        pp.setOnClickListener(listener);
        post.setOnClickListener(listener);
        bakery.setOnClickListener(listener);
        vegan.setOnClickListener(listener);
        dessert.setOnClickListener(listener);
        veganskoe.setOnClickListener(listener);
        drink.setOnClickListener(listener);
        return view;
    }

    public static void update(){
        categoryAdapter = new CategoryAdapter(rvCategory.getContext(), DbImitation.getCategories(), searchFragment);
        rvCategory.setAdapter(categoryAdapter);
    }
}