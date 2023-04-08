package com.mytracker.familygpstracker.Models;



public class CreateUser
{
    public String name;
    public String email;
    public String password;
    public String date;
    public String circlecode;
    public String userid;
    public String issharing;
    public String lat;
    public String lng;
    public String profile_image;


    public CreateUser()
    {}

    public CreateUser(String name, String email, String password, String date, String circlecode, String userid, String issharing, String lat, String lng,String profile_image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = date;
        this.circlecode = circlecode;
        this.userid = userid;
        this.issharing = issharing;
        this.lat = lat;
        this.lng = lng;
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCirclecode() {
        return circlecode;
    }

    public void setCirclecode(String circlecode) {
        this.circlecode = circlecode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getIssharing() {
        return issharing;
    }

    public void setIssharing(String issharing) {
        this.issharing = issharing;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
