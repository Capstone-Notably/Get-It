package com.codeup.models;

/**
 * Created by roxana on 7/2/17.
 */
public class UserItem {
    private String name;
    private String imgUrl;
    private float price;
    private int quantity;
    private String barcode;
    private boolean favorite;

    public UserItem() {
    }

    public UserItem(String name, String imgUrl, float price, int quantity, String barcode, boolean favorite) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.quantity = quantity;
        this.barcode = barcode;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
