package com.codeup.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by roxana on 6/29/17.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column()
    private String imgUrl;

    @OneToMany(mappedBy = "user")
    private List<Category> categories;

    @OneToMany(mappedBy = "user")
    private List<Item> items;

    public User() {
    }

    public User(String username, String email, String phone, String password, String imgUrl) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.imgUrl = imgUrl;
    }

    public User(User user) {
        id = user.id;
        username = user.username;
        password = user.password;
        email = user.email;
        phone = user.phone;
        imgUrl = user.imgUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
