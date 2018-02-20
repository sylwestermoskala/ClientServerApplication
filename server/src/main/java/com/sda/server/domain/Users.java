package com.sda.server.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users {

    private Integer id;
    private String password;

    @Id
    private String email;

    public Users() {
    }

    public Users(String password, String username) {
        this.password = password;
        this.email = username;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}