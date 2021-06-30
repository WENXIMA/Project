package com.example.login;

public class Course {
    private int id;
    private String courseCode;
    private String courseName;
    private String instructor;
    private String days;
    private String hours;
    private String description;
    private String capacity;



    // constructors
    public Course(){}
    public Course(String courseName, String courseCode){
        this.courseCode = courseCode;
        this.courseName = courseName;
    }
    public Course(String courseCode, String courseName,String id, String instructor){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.id = Integer.parseInt(id);
        this.instructor=instructor;
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

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDays() {
        return days;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getHours() {
        return hours;
    }
}
