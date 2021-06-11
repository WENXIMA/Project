package com.example.login;

import android.widget.EditText;

public class User {
    private String username;
    private String password;
    private int ID;
    private String userType;
    private String lastName;
    private String firstName;


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

    public User(String name, String password,String lastName, String firstName){
        super();
        this.username = name;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public User(String name, String password, String userType, String lastName, String firstName){
        super();
        this.username = name;
        this.password = password;
        this.userType = userType;
        this.lastName = lastName;
        this.firstName = firstName;
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
    public void setLastName(String lastName){this.lastName = lastName;}
    public String getLastName(){return this.lastName;}
    public void setFirstName(String firstName){this.firstName=firstName;}
    public String gerFirstName(){return this.firstName;}
}