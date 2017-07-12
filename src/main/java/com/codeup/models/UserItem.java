package com.codeup.models;

import javax.persistence.*;

/**
 * Created by roxana on 7/1/17.
 */
@Entity
@Table(name = "users_items")
public class UserItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private double price;

    @Column
    private int quantity;

    @Column
    private String barcode;

    @Column
    private boolean favorite;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public UserItem() {
    }

    public UserItem(double price, int quantity, String barcode, boolean favorite, User user, Item item) {
        this.price = price;
        this.quantity = quantity;
        this.barcode = barcode;
        this.favorite = favorite;
        this.user = user;
        this.item = item;
    }

    public UserItem(User user, Item item) {
        this.user = user;
        this.item = item;
    }

    public UserItem(User user, Item item, int quantity, double price) {
        this.user = user;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
