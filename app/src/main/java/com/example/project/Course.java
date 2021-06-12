package com.example.project;

public class Course {
    private int id;
    private String courseCode;
    private String courseName;

    public Course(){
    }

    public Course(String courseCode, String courseName){
        this.courseCode=courseCode;
        this.courseName=courseName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }
}
