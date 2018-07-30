package com.app.mashkabari.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelTest implements Serializable
{

    @SerializedName("homelist")
    @Expose
    private ArrayList<ModelHomelist> homelist = null;
    private final static long serialVersionUID = 7737782202906512792L;

    public ArrayList<ModelHomelist> getHomelist() {
        return homelist;
    }

    public void setHomelist(ArrayList<ModelHomelist> homelist) {
        this.homelist = homelist;
    }
}
