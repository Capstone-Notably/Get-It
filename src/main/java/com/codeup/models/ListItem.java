package com.codeup.models;

import javax.persistence.*;

/**
 * Created by roxana on 7/3/17.
 */
@Entity
@Table(name = "list_items")
public class ListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "glist_id")
    private GroceryList glist;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public ListItem() {
    }

    public ListItem(GroceryList glist, Item item) {
        this.glist = glist;
        this.item = item;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
