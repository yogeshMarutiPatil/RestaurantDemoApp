package com.example.restaurantsdemoapp.utils;

import androidx.room.TypeConverter;

import com.example.restaurantsdemoapp.model.pojo.SortingValues;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;

public class DataConvertor implements Serializable {
    @TypeConverter
    public String fromOptionValuesList(SortingValues optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<SortingValues>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter
    public SortingValues toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<SortingValues>() {
        }.getType();
        SortingValues productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }


}
