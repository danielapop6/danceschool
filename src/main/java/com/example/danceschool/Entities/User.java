package com.example.danceschool.Entities;

import com.example.danceschool.Utils.Utils;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {
    @Id
    @Column(name = "username",length = 20, nullable = false, unique = true)
    private String username;

    @Column(name = "password",length = 50, nullable = false)
    private String password;

    @Column(name = "admin",nullable = false)
    private Boolean admin;

    public User() {
    }

    public User(String username,String password, Boolean admin) {
        this.username = username;
        this.setPassword(password);
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = Utils.getMD5Hash(password);
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
