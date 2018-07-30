package com.app.mashkabari.model.modelCategories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelCategories implements Serializable
{
    @SerializedName("homelist")
    @Expose
    private ArrayList<ModelCategoriesHomelist> homelist = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    private final static long serialVersionUID = 499890434503012819L;

    public ArrayList<ModelCategoriesHomelist> getHomelist() {
        return homelist;
    }

    public void setHomelist(ArrayList<ModelCategoriesHomelist> homelist) {
        this.homelist = homelist;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
