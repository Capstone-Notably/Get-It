package com.codeup.models;

/**
 * Created by roxana on 7/2/17.
 */
public class CustomItem {
    private long id;
    private String name;
    private String imgUrl;
    private float price;
    private int quantity;
    private String barcode;
    private boolean favorite;
    private long listId;
    private long categoryId;

    public CustomItem() {
    }

    public CustomItem(long id, String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.id =id;
    }

    public CustomItem(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.imgUrl = item.getImgUrl();
        this.categoryId = item.getCategory().getId();
    }

    public CustomItem(long id, String name, String imgUrl, float price, int quantity, String barcode, boolean favorite) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.quantity = quantity;
        this.barcode = barcode;
        this.favorite = favorite;
    }

    public CustomItem(long id, String name, String imgUrl, float price, int quantity, String barcode, boolean favorite, long listId) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.quantity = quantity;
        this.barcode = barcode;
        this.favorite = favorite;
        this.listId = listId;
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

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
