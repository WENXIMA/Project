package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InstructorCourseManage extends AppCompatActivity {

    ListView listView;
    MyDBhandler myDBhandler = new MyDBhandler(this);
    String name;
    CourseAdapter adapter;
    Button back;
//    List<Course> test;

    private List<Course> courses = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_course_manage);


        name = getIntent().getStringExtra("name");

        listView = (ListView)findViewById(R.id.courseList);

        courses = myDBhandler.findCourseByInstructor(name);

//        test = new LinkedList<>();

//        test.add(new Course("testa","testCodea"));
//        test.add(new Course("testb","testCodeb"));

//        adapter = new CourseAdapter((LinkedList<Course>)test,this);

        adapter = new CourseAdapter((LinkedList<Course>)courses,this);
        listView.setAdapter(adapter);

        back = findViewById(R.id.ListReturn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstructorCourseManage.super.onBackPressed();
            }
        });

    }
}
