package com.jonathanbrito.restgithub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {

    @SerializedName("items")
    @Expose
    private List<Item> items;

    public List<Item> getItem(){
        return items;
    }
    public void setItems(List<Item> items){
        this.items = items;
    }
}
