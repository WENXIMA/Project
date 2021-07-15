package com.example.login;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentWelcome extends AppCompatActivity {
    Button enrollPageButton, viewCoursesButton, logout;
    TextView studentWelcomeHeaderText, welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_welcome);
        enrollPageButton = findViewById(R.id.enrollPageButton);
        viewCoursesButton = findViewById(R.id.viewCoursesButton);
        logout = findViewById(R.id.LogOut);
        studentWelcomeHeaderText = findViewById(R.id.studentWelcomeHeaderText);
        welcome = findViewById(R.id.textView3);

        welcome.setText("Welcome "+ MainActivity.user.getUsername()+ ", you are logged in as a student");

        enrollPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentWelcome.this, StudentSearchEnroll.class));
            }
        });

        viewCoursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentWelcome.this, StudentViewCourses.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentWelcome.this,MainActivity.class));
            }
        });
    }


}