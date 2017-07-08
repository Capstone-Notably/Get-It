package com.codeup.models;

import javax.persistence.*;

/**
 * Created by roxana on 7/7/17.
 */
@Entity
@Table(name = "users_glists")
public class UserGList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "glist_id")
    private GroceryList glist;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserGList() {
    }

    public UserGList(GroceryList glist, User user) {
        this.glist = glist;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GroceryList getGlist() {
        return glist;
    }

    public void setGlist(GroceryList glist) {
        this.glist = glist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
