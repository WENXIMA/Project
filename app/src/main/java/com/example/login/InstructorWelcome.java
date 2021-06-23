package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorWelcome extends AppCompatActivity {
    Button logout,assign;
    TextView welcome;
    User instructor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_welcome);
        logout= findViewById(R.id.LogOut);
        welcome=findViewById(R.id.textView3);
        assign=findViewById(R.id.assign);
        instructor= MainActivity.user;
        welcome.setText("Welcome "+ instructor.getUsername()+ ", you are logged in as an Instructor");

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InstructorWelcome.this,InstructorSearchAssign.class));
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