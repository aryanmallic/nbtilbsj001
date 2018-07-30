package com.app.mashkabari.model.modlDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelDetailsProduct implements Serializable
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
    private ArrayList<ModelDetailsImage> images = null;
    @SerializedName("unit_prices")
    @Expose
    private ArrayList<ModelDetailsUnitPrice> unitPrices = null;
    private final static long serialVersionUID = 7072907676382983028L;

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

    public ArrayList<ModelDetailsImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<ModelDetailsImage> images) {
        this.images = images;
    }

    public ArrayList<ModelDetailsUnitPrice> getUnitPrices() {
        return unitPrices;
    }

    public void setUnitPrices(ArrayList<ModelDetailsUnitPrice> unitPrices) {
        this.unitPrices = unitPrices;
    }
}
