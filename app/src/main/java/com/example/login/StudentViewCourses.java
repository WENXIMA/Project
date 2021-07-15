package com.example.login;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentViewCourses extends AppCompatActivity {
    TextView coursesHeader;
    ListView courseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_courses);

        coursesHeader = findViewById(R.id.coursesHeader);
        courseListView = findViewById(R.id.courseListView);

        MyDBhandler db = new MyDBhandler(this);
        ArrayList<Course> courses = db.getAllCourseData();
        ArrayList<Course> enrolledCourses = new ArrayList<Course>();

        for(Course course : courses){
            if(course.getStudentList() == null){
                continue;
            } else if(course.getStudentList().contains(MainActivity.user.getUsername())){
                enrolledCourses.add(course);
            }
        }

        ArrayAdapter<Course> arrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.activity_list_item, android.R.id.text1, enrolledCourses);
        courseListView.setAdapter(arrayAdapter);
    }
}
