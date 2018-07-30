package com.app.mashkabari.model.modlDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelDetails implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("product")
    @Expose
    private ModelDetailsProduct product;
    @SerializedName("status")
    @Expose
    private Boolean status;
    private final static long serialVersionUID = 7510542955592094006L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ModelDetailsProduct getProduct() {
        return product;
    }

    public void setProduct(ModelDetailsProduct product) {
        this.product = product;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
