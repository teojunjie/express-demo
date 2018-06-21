package com.example.tejj.express_demo;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name") // the json format name , if not the name of the variable must be exactly the same
    private String name;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("email")
    private String email;
    @SerializedName("friends")
    private Integer friends;

    public User(String name, String birthday, String email, Integer friends){
        this.name=name;
        this.birthday=birthday;
        this.email=email;
        this.friends=friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFriends() {
        return friends;
    }

    public void setFriends(Integer friends) {
        this.friends = friends;
    }
}
