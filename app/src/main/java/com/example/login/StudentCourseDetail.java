package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentCourseDetail extends AppCompatActivity {
    private int id;
    private Course course;
    private MyDBhandler myDBhandler;
    TextView Des, Days, Hours, Name, instructor;
    Button returnPre, drop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_details);

        id = getIntent().getIntExtra("id", -1);

        myDBhandler = new MyDBhandler(this);

        course = myDBhandler.findCourseByID(id);

        Name = findViewById(R.id.enrollCourseDetailsCourseName);

        instructor = findViewById(R.id.studentCourseInstructor);

        returnPre = findViewById(R.id.studentReturnPre);

        drop = findViewById(R.id.studentCourseDrop);

        Des = findViewById(R.id.studentCourseDes);

        Days = findViewById(R.id.studentCourseDays);

        Hours = findViewById(R.id.studentCourseHours);

        returnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(StudentCourseDetail.this, StudentAllCourse.class);
                startActivity(temp);
                finish();
            }
        });

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBhandler.dropCourse(course,MainActivity.user.getUsername());
                Toast.makeText(StudentCourseDetail.this,"Drop Course successfully",Toast.LENGTH_SHORT).show();
                Intent temp = new Intent(StudentCourseDetail.this, StudentAllCourse.class);
                startActivity(temp);
                finish();
            }
        });

        Name.setText(course.getCourseName());

        if(course.getInstructor() == null || course.getInstructor().equals(""))
            instructor.setText("No Instructor");
        else
            instructor.setText(course.getInstructor());

        if (course.getDescription() == null || course.getDescription().equals(""))
            Des.setText("No Description");
        else
            Des.setText(course.getDescription());

        if (course.getDays() == null || course.getDays().equals(""))
            Days.setText("Not Set");
        else
            Days.setText(course.getDays());

        if (course.getHours() == null || course.getHours().equals(""))
            Hours.setText("Not Set");
        else
            Hours.setText(course.getHours());
    }
}
