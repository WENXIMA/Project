package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorWelcome extends AppCompatActivity {

    Button logout,assign,viewall,manage;
    TextView welcome;
    User instructor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_welcome);
        instructor = MainActivity.user;
        logout = findViewById(R.id.LogOut);
        welcome = findViewById(R.id.textView3);
        assign = findViewById(R.id.assign);
        manage = findViewById(R.id.ConnectCourseManager);
        viewall = findViewById(R.id.viewallcourse);
        welcome.setText("Welcome " + instructor.getUsername() + ", you are logged in as an Instructor");

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstructorWelcome.this,InstructorCourseManage.class);
                intent.putExtra("name",instructor.getUsername());
                startActivity(intent);
            }
        });

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InstructorWelcome.this,InstructorSearchAssign.class));
            }
        });

        viewall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(InstructorWelcome.this,ViewAllCourse.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InstructorWelcome.this,MainActivity.class));
            }
        });
    }
}