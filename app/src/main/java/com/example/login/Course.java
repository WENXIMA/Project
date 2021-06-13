package com.example.login;

public class Course {
    private int id;
    private String courseCode;
    private String courseName;

    // constructors
    public Course(){}
    public Course(String courseName, String courseCode){
        this.courseCode = courseCode;
        this.courseName = courseName;
    }
    public Course(String courseCode, String courseName,String id){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.id = Integer.parseInt(id);
    }

    // getters and setters
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    @Override
    public String toString(){
        return "Course{id = " + id + ", course code = "+ courseCode + ", course name = " + courseName + "}";
    }
}
