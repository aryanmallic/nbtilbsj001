package com.app.mashkabari.model.modelCategories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelCategoriesHomelist implements Serializable
{


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("products")
    @Expose
    private ArrayList<ModelCategoriesProduct> products = null;
    private final static long serialVersionUID = 2979517952966785246L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ModelCategoriesProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ModelCategoriesProduct> products) {
        this.products = products;
    }
}
