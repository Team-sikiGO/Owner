package com.example.owner;

import android.graphics.drawable.Drawable;

public class MenuItem {
    public String food_name;
    public String price;
    public Drawable food_image;

    public String getFood_name() {
        return food_name;
    }
    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public Drawable getFood_image() {
        return food_image;
    }
    public void setFood_image(Drawable food_image) {
        this.food_image = food_image;
    }

}
