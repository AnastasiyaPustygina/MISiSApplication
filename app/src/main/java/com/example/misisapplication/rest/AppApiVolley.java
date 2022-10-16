package com.example.misisapplication.rest;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.misisapplication.DbImitation;
import com.example.misisapplication.adapters.AllDishFromCategoryAdapter;
import com.example.misisapplication.adapters.CategoryAdapter;
import com.example.misisapplication.adapters.DishesRecyclerAdapter;
import com.example.misisapplication.domain.Dish;
import com.example.misisapplication.domain.mapper.CategoryMapper;
import com.example.misisapplication.domain.mapper.DishMapper;
import com.example.misisapplication.domain.mapper.PersonMapper;
import com.example.misisapplication.domain.mapper.RecipeMapper;
import com.example.misisapplication.fragments.AllDishFromCategoryFragment;
import com.example.misisapplication.fragments.RecipeFragment;
import com.example.misisapplication.fragments.ResultDishFragment;
import com.example.misisapplication.fragments.SearchFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppApiVolley implements AppApi {
    private static final String BASE_URL = "http://192.168.1.34:8082";
    private final Context context;
    private Response.ErrorListener errorListener;

    public AppApiVolley(Context context) {
        this.context = context;
        errorListener = new ErrorListenerImpl();
    }

    @Override
    public void findAllCategories() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/category/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Log.e("findAllCategories", String.valueOf(jsonObject));
                                DbImitation.addCategory(new CategoryMapper().getFromJsonArray(jsonObject));
                            }
                        } catch (JSONException e) {
                            Log.d("CATEGORY_LIST", e.getMessage());
                        }
                        SearchFragment.update();
                    }
                },
                errorListener
        );
        queue.add(jsonArrayRequest);
    }

    @Override
    public void findDishesByCategoryId(int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/dish/category/" + id;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                DbImitation.addDish(new DishMapper().getFromJsonArray(jsonObject));
                            }
                        } catch (JSONException e) {
                            Log.d("DISHES_LIST", e.getMessage());
                        }
                        AllDishFromCategoryFragment.update();
                    }
                },
                errorListener
        );
        queue.add(jsonArrayRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void findDishesByCategoryIds(List<Integer> ids) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringBuilder stringIds = new StringBuilder();
        for (int i = 0; i < ids.size() - 1; i++) {
            stringIds.append(ids.get(i)).append(",");
        }
        stringIds.append(ids.get(ids.size() - 1));
        String url = BASE_URL + "/dish/categories/?ids=" + stringIds.toString();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("findDishesByCategoryIds", String.valueOf(response));
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                DbImitation.addDish(new DishMapper().getFromJsonArray(jsonObject));
                            }
                        } catch (JSONException e) {
                            Log.d("DISHES_LIST", e.getMessage());
                        }
                        ResultDishFragment.update();
                    }
                },
                errorListener
        );
        queue.add(jsonArrayRequest);
    }

    @Override
    public void findTenDishesByCategoryId(int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/dish/ten/category/" + id;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("findTenDishes", String.valueOf(response));
                        List<Dish> dishes = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                dishes.add(new DishMapper().getFromJsonArray(jsonObject));
                            }
                        } catch (JSONException e) {
                            Log.d("DISHES_LIST", e.getMessage());
                        }
                        DbImitation.addValueMap(id, dishes);
                        Log.e("aav" + id, dishes + "");
                        CategoryAdapter.updateDish(id);
                    }
                },
                errorListener
        );
        queue.add(jsonArrayRequest);
    }

    @Override
    public void findDishById(int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/dish/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("findDishById", String.valueOf(response));
                        DbImitation.setCurrentDish((new DishMapper().getFromJsonArray(response)));
                    }
                },
                errorListener
        );
        queue.add(jsonObjectRequest);
    }

    @Override
    public void insertPerson() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/person";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        DbImitation.setPersonId(new PersonMapper().getFromJsonArray(response).getId());
                    }
                },
                errorListener
        );
        queue.add(jsonObjectRequest);
    }

    @Override
    public void findProductByIngredientId(int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/product/ingredient/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("findProdByIngredId", response.toString());
                    }
                },
                errorListener
        );
        queue.add(jsonObjectRequest);
    }

    @Override
    public void findRecipeByDishId(int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/recipe/dish/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        DbImitation.setCurrentRecipe(RecipeMapper.getFromJsonArray(response));
                        RecipeFragment.fillRecipe(RecipeMapper.getFromJsonArray(response));
                    }
                },
                errorListener
        );
        queue.add(jsonObjectRequest);
    }

    @Override
    public void findRecipeByPersonId(int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/recipe/person/" + id;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                DbImitation.addRecipe(RecipeMapper.getFromJsonArray(response.getJSONObject(i)));
                            }
                        } catch (JSONException e) {
                            Log.d("RECIPE_LIST", e.getMessage());
                        }
                    }
                },
                errorListener
        );
        queue.add(jsonArrayRequest);
    }

    @Override
    public void deleteRecipeFromFavouritesByPersonIdAndRecipeId(int personId, int recipeId) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/recipe/" + recipeId + "/person/" + personId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(@Nullable JSONObject response) {
                    }
                },
                errorListener
        ) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject result = null;
                    if (jsonString.length() > 0)
                        result = new JSONObject(jsonString);
                    return Response.success(result,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException | JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        queue.add(jsonObjectRequest);
    }

    @Override
    public void addRecipeToFavouritesByPersonIdAndRecipeId(int personId, int recipeId) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/recipe/" + recipeId + "/person/" + personId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(@Nullable JSONObject response) {
                    }
                },
                errorListener
        ) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject result = null;
                    if (jsonString.length() > 0)
                        result = new JSONObject(jsonString);
                    return Response.success(result,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException | JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        queue.add(jsonObjectRequest);
    }

    private class ErrorListenerImpl implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError e) {
            Log.e("AppApiErrorResponse", e.toString());
        }
    }
}
