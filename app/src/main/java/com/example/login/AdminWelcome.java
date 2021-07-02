package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminWelcome extends AppCompatActivity {

    Button logout, courses, users;
    TextView header, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);

        logout = findViewById(R.id.LogOut);
        header = findViewById(R.id.adminWelcomeHeaderText);
        welcome = findViewById(R.id.textView3);
        courses = findViewById(R.id.Courses);
        users = findViewById(R.id.Users);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminWelcome.this,MainActivity.class));
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminWelcome.this,ManageAccounts.class));
            }
        });

        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminWelcome.this,AdminCourses.class));
            }
        });
    }
}