package com.example.admin.first_one.model;

/**
 * Created by admin on 2018/3/10.
 */

public class MyModel {
    private int id;
    private String name;
    private String image;
    private String email;
    private String phone;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image=image;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
}
