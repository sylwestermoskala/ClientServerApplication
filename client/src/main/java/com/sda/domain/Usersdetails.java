package com.sda.domain;

public class Usersdetails {

    private Integer id;
    private String email;
    private String country;
    private String gender;

    public Usersdetails(){}

    public Usersdetails(String email, String country, String gender) {
        this.email = email;
        this.country = country;
        this.gender = gender;
    }

    public Usersdetails(String country, String gender) {
        this.country = country;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}



