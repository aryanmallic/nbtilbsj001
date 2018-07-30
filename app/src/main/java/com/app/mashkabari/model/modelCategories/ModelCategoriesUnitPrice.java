package com.app.mashkabari.model.modelCategories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelCategoriesUnitPrice implements Serializable
{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("unit_currency")
    @Expose
    private String unitCurrency;
    @SerializedName("unit_price")
    @Expose
    private String unitPrice;
    @SerializedName("is_default")
    @Expose
    private int isDefault;
    private final static long serialVersionUID = -8838173575093628385L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitCurrency() {
        return unitCurrency;
    }

    public void setUnitCurrency(String unitCurrency) {
        this.unitCurrency = unitCurrency;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    /*public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }*/

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
