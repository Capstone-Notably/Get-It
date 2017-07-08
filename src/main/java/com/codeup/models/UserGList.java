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
}
