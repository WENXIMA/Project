package com.example.login;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentWelcome extends AppCompatActivity {
    Button logout;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_welcome);
        logout= findViewById(R.id.LogOut);
        welcome=findViewById(R.id.textView3);
        Button enroll = findViewById(R.id.studentSearchEnroll);
        Button allCourse = findViewById(R.id.studentAllCourse);

        welcome.setText("Welcome "+ MainActivity.user.getUsername()+ ", you are logged in as a student");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentWelcome.this,MainActivity.class));
            }
        });

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentWelcome.this,StudentSearchEnroll.class));
            }
        });

        allCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentWelcome.this,StudentAllCourse.class));
            }
        });

    }


}