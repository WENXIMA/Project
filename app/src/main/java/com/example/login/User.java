package com.example.login;

public class User {
    private String username;
    private String password;
    private int ID;
    private String userType;

    public User(){}

    public User(String name, String password){
        super();
        this.username = name;
        this.password = password;
    }

    public User(String name, String password, String userType){
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
    @Override
    public String toString(){
        return "User{type =" + userType + ", username = "+ username+",password = "+ password + "}";
    }



}
