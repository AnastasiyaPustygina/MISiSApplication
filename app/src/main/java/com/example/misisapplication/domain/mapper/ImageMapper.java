package com.example.misisapplication.domain.mapper;

import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.misisapplication.domain.Image;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Optional;
public class ImageMapper {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Image getFromJsonArray(JSONObject object){
        Image image = null;
        try {
            image = new Image(object.getInt("id"), object.getString("path"));
        } catch (JSONException e) {
            Log.e("ImageMapper", e.getMessage());
        }
        return image;
    }
}