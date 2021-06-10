package com.example.login;

import android.widget.EditText;

public class User {
    public String username;
    public String password;
    public int ID;
    public String userType;

    public User(){}

    public User(String name, String password){
        super();
        this.username = name;
        this.password = password;
    }

    public User(String name, String password, String userType){
        super();
        this.username = name;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String Username){
        this.username=username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String Password){
        this.password=password;
    }
    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    public String getUserType(){
        return userType;
    }
    public void setUserType(String userType){
        this.userType=userType;
    }
    @Override
    public String toString(){
        return "User{id =" + ID + ", username = "+ username+",password = "+ password + "}";
    }
}