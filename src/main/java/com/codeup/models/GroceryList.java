package com.codeup.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by roxana on 7/3/17.
 */
@Entity
@Table(name = "grocery_lists")
public class GroceryList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "glist")
    private List<ListItem> listItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "glist")
    private List<UserGList> userGLists;

    public GroceryList() {
    }

    public GroceryList(String name) {
        this.name = name;
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

    public List<ListItem> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }

    public List<UserGList> getUserGLists() {
        return userGLists;
    }

    public void setUserGLists(List<UserGList> userGLists) {
        this.userGLists = userGLists;
    }
}
