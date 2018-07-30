package com.app.mashkabari.model.modelCategories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelCategoriesProduct implements Serializable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("images")
    @Expose
    private ArrayList<ModelCategoriesImage> images = null;
    @SerializedName("unit_prices")
    @Expose
    private ArrayList<ModelCategoriesUnitPrice> unitPrices = null;
    private final static long serialVersionUID = -4586027335683714088L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ModelCategoriesImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<ModelCategoriesImage> images) {
        this.images = images;
    }

    public ArrayList<ModelCategoriesUnitPrice> getUnitPrices() {
        return unitPrices;
    }

    public void setUnitPrices(ArrayList<ModelCategoriesUnitPrice> unitPrices) {
        this.unitPrices = unitPrices;
    }
}
