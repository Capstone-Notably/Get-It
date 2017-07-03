package com.codeup.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by roxana on 6/28/17.
 */
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String imgUrl;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Item> items;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<UserCategory> userCategories;


    public Category(String name, String imgUrl, User user) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.user = user;
    }

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserCategory> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(List<UserCategory> userCategories) {
        this.userCategories = userCategories;
    }
}
