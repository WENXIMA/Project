package com.example.login;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructorViewStudents extends AppCompatActivity {

    TextView enrolledStudentsHeader, enrolledStudentsSubheader;
    String courseCode, courseName;
    ListView studentView;
    List enrolledStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_enrolled_students);

        courseCode = getIntent().getStringExtra("courseCode");
        courseName = getIntent().getStringExtra("courseName");
        studentView = findViewById(R.id.studentList);

        enrolledStudentsHeader = findViewById(R.id.enrolledStudentsHeader);
        enrolledStudentsSubheader = findViewById(R.id.enrolledStudentsSubheader);
        enrolledStudentsSubheader.setText("Course name: " + courseName);

        // manually set these to use the findCourseInstructor method in DBhandler
        InstructorSearchAssign.editTextCourseCodee = courseCode;
        InstructorSearchAssign.editTextCourseNamee = courseName;

        MyDBhandler db = new MyDBhandler(this);
        Course course = db.findCourseInstructor(courseCode);

        if(course.getStudentList() == null){
            enrolledStudents = new ArrayList<String>();
            enrolledStudents.add("");
        } else {
            String[] enrolledStudentArray = course.getStudentList().split(";");
            enrolledStudents = Arrays.asList(enrolledStudentArray);
        }

        ArrayAdapter<Course> arrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.activity_list_item, android.R.id.text1, enrolledStudents);
        studentView.setAdapter(arrayAdapter);
    }
}
