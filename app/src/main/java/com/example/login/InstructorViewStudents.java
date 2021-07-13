package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InstructorViewStudents extends AppCompatActivity {

    TextView enrolledStudentsHeader, enrolledStudentsSubheader;
    String courseName;
    RecyclerView studentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_enrolled_students);

        courseName = getIntent().getStringExtra("courseName");
        studentView = findViewById(R.id.studentList);

        enrolledStudentsHeader = findViewById(R.id.enrolledStudentsHeader);
        enrolledStudentsSubheader = findViewById(R.id.enrolledStudentsSubheader);
        enrolledStudentsSubheader.setText("Course name: " + courseName);

        MyDBhandler db = new MyDBhandler(this);
        // get list of students enrolled in the specified course via DBhandler
        // ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<Student>(this, android.R.layout.activity_list_item,android.R.id.text1, students);
        // studentView.setAdapter(arrayAdapter);
    }
}
