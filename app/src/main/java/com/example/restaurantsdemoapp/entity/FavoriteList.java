package com.example.restaurantsdemoapp.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.restaurantsdemoapp.model.pojo.SortingValues;
import com.example.restaurantsdemoapp.utils.DataConvertor;

@Entity(tableName = "favoritelist")
public class FavoriteList {

    @PrimaryKey
    @NonNull
    private String name;
    @ColumnInfo(name = "status")
    private String status;
    @TypeConverters({DataConvertor.class})
    @ColumnInfo(name = "sortingValues")
    private SortingValues sortingValues;
    @ColumnInfo(name = "sortElement")
    private String sortElement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SortingValues getSortingValues() {
        return sortingValues;
    }

    public void setSortingValues(SortingValues sortingValues) {
        this.sortingValues = sortingValues;
    }

    public String getSortElement() {
        return sortElement;
    }

    public void setSortElement(String sortElement) {
        this.sortElement = sortElement;
    }
}
