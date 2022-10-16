package com.example.misisapplication.domain.mapper;

import android.util.Log;

import com.example.misisapplication.domain.Category;
import com.example.misisapplication.domain.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;

public class PersonMapper {
    public Person getFromJsonArray(JSONObject object) {
        Person person = null;
        try {
        person = new Person(object.getInt("id"), Collections.emptyList());
    } catch (
    JSONException e) {
        Log.e("PersonMapper", e.getMessage());
    }
        return person;
}
}
