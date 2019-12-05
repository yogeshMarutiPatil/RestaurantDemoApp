
package com.example.restaurantsdemoapp.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SortingValues {

    @SerializedName("bestMatch")
    @Expose
    private Double bestMatch;
    @SerializedName("newest")
    @Expose
    private Double newest;
    @SerializedName("ratingAverage")
    @Expose
    private Double ratingAverage;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("averageProductPrice")
    @Expose
    private Integer averageProductPrice;
    @SerializedName("deliveryCosts")
    @Expose
    private Integer deliveryCosts;
    @SerializedName("minCost")
    @Expose
    private Integer minCost;

    public Double getBestMatch() {
        return bestMatch;
    }

    public void setBestMatch(Double bestMatch) {
        this.bestMatch = bestMatch;
    }

    public Double getNewest() {
        return newest;
    }

    public void setNewest(Double newest) {
        this.newest = newest;
    }

    public Double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getAverageProductPrice() {
        return averageProductPrice;
    }

    public void setAverageProductPrice(Integer averageProductPrice) {
        this.averageProductPrice = averageProductPrice;
    }

    public Integer getDeliveryCosts() {
        return deliveryCosts;
    }

    public void setDeliveryCosts(Integer deliveryCosts) {
        this.deliveryCosts = deliveryCosts;
    }

    public Integer getMinCost() {
        return minCost;
    }

    public void setMinCost(Integer minCost) {
        this.minCost = minCost;
    }

}
