package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

public class StudentAllCourse extends AppCompatActivity {
    ListView listView;
    MyDBhandler myDBhandler = new MyDBhandler(this);
    String name;
    StudentCourseAdapter adapter;
    Button back;
//    List<Course> test;

    private List<Course> courses = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_manage);


        name = MainActivity.user.getUsername();

        listView = findViewById(R.id.StudentCourseList);

        courses = myDBhandler.findCoursesByStudent(MainActivity.user.getUsername());

//        test = new LinkedList<>();

//        test.add(new Course("testa","testCodea"));
//        test.add(new Course("testb","testCodeb"));

//        adapter = new CourseAdapter((LinkedList<Course>)test,this);

        adapter = new StudentCourseAdapter((LinkedList<Course>)courses,this);
        listView.setAdapter(adapter);

        back = findViewById(R.id.StudentListReturn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentAllCourse.super.onBackPressed();
            }
        });

    }
}
