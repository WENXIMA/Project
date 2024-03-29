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
    private String studentList;


    // constructors
    public Course(){}
    public Course(String courseName, String courseCode){
        this.courseCode = courseCode;
        this.courseName = courseName;
    }
    public Course(String courseCode, String courseName, String id, String instructor, String days, String studentList){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.id = Integer.parseInt(id);
        this.instructor = instructor;
        this.days = days;
        this.studentList = studentList;
    }

    public Course(String courseCode, String courseName, String id, String instructor, String days, String hours, String studentList){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.id = Integer.parseInt(id);
        this.instructor = instructor;
        this.days = days;
        this.studentList = studentList;
        this.hours = hours;
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
        return "Course #" + id + "\n course code = "+ courseCode + "\n course name = " + courseName +"\n Instructor name = "+ instructor;
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

    public void setStudentList(String studentList) { this.studentList = studentList; }

    public String getStudentList() { return studentList; }
}
