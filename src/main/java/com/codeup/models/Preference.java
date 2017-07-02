package com.codeup.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by roxana on 7/1/17.
 */
@Entity
@Table(name = "preferences")
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preference")
    List<Item> items;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preference")
    List<UserCategory> userCategories;

    public Preference(String name) {
        this.name = name;
    }

    public Preference() {
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<UserCategory> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(List<UserCategory> userCategories) {
        this.userCategories = userCategories;
    }
}
