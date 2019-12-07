
package com.example.restaurantsdemoapp.model.pojo;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Restaurant {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sortingValues")
    @Expose
    private SortingValues sortingValues;

    private String sortedElement;

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

    public String getSortedElement() {
        return sortedElement;
    }

    public void setSortedElement(String sortedElement) {
        this.sortedElement = sortedElement;
    }

}
