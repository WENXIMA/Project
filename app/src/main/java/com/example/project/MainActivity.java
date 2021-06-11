package com.example.project;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button student;
    Button instructor;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        student = (Button) findViewById(R.id.Student);
        instructor = (Button) findViewById(R.id.Instructor);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudentWelcome();
            }
        });



        instructor = (Button) findViewById(R.id.Instructor);
        instructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstructorWelcome();
            }
        });

        add= (Button) findViewById(R.id.AddCourse);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCourse();
            }
        });

    }

    public void openStudentWelcome() {
        Intent intent = new Intent(this, StudentWelcome.class);
        startActivity(intent);
    }

    public void openInstructorWelcome() {
        Intent intent = new Intent(this, InstructorWelcome.class);
        startActivity(intent);
    }
//

    public void openAddCourse(){
        Intent intent = new Intent(this, AddCourse.class);
        startActivity(intent);
    }


}