package com.example.login;

public class User {
    public String username;
    public String password;
    public int ID;
    public String UserType;

    public User(String name,String password){
        super();
        this.username=name;
        this.password=password;
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
    public String UserType(){
        return UserType;
    }
    public void setUserType(String UserType){
        this.UserType=UserType;
    }
    @Override
    public String toString(){
        return "User{id =" + ID + ", username = "+ username+",password = "+ password + "}";
    }
}
