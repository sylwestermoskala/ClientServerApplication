package com.sda.server.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usersdetails {

    @Id
    private String email;
    private String country;
    private String gender;

    public Usersdetails(){}

    public Usersdetails(String email, String gender, String country) {
        this.email = email;
        this.country = country;
        this.gender = gender;
    }

    public Usersdetails(String country, String gender) {
        this.country = country;
        this.gender = gender;
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



