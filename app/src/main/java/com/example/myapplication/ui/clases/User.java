package com.example.myapplication.ui.clases;

import com.google.gson.annotations.SerializedName;

public class User {
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("pass")
    private String pass;

    public User(String id,String name, String email,String pass ) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.pass = pass;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}
