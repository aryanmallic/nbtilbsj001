package com.app.mashkabari.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelHomelist {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("child")
    @Expose
    private ArrayList<ModelChild> child = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<ModelChild> getChild() {
        return child;
    }

    public void setChild(ArrayList<ModelChild> child) {
        this.child = child;
    }
}
