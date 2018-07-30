package com.app.mashkabari.model.modlDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelDetailsImage implements Serializable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    private final static long serialVersionUID = -9216757361471429941L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
