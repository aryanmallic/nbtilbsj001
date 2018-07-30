package com.app.mashkabari.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelChild implements Serializable
{
    @SerializedName("child")
    @Expose
    private String child;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    private final static long serialVersionUID = -4274535112444511476L;

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
