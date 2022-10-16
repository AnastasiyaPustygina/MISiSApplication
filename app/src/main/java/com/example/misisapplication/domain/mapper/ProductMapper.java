package com.example.misisapplication.domain.mapper;

import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.misisapplication.domain.Image;
import com.example.misisapplication.domain.Product;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Optional;
public class ProductMapper {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Product getFromJsonArray(JSONObject object){
        Product product = null;
        try{
            product = new Product(object.getInt("id"), object.getString("name"),
                    object.getInt("caloriesNumber"), object.getInt("glycemicIndex"),
                    object.getInt("protein"), object.getInt("fat"),
                    object.getInt("carbohydrate"),
                    new ImageMapper().getFromJsonArray(object.getJSONObject("image")));
        }catch (JSONException e){
            Log.e("ProductMapper", e.getMessage());
        }
        return product;
    }
}