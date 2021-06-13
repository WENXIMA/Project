package com.example.login;


public class Student extends User{
    public Student(){}

    public Student(String name, String password){
        super(name, password, "student");
    }
}