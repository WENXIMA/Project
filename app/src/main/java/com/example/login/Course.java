package com.example.login;

public class Course {

    private String courseName;
    private String courseCode;
    private int capacity;
    private String instructorName;
    private int id;


    public Course(String courseName,String courseCode)
    {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public void setCourseName(String name)
    {
        this.courseName = name;
    }

    public void setCourseCode(String code)
    {
        this.courseCode = code;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public void setInstructorName(String name)
    {
        this.instructorName = name;
    }

    public int getCapacity()
    {
        return this.capacity;
    }

    public String getCourseName()
    {
        return this.courseName;
    }

    public String getCourseCode()
    {
        return this.courseCode;
    }

    public String getInstructorName()
    {
        return this.instructorName;
    }




}
