package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
public class ViewAllCourse extends AppCompatActivity{
    Button viewbutton;
    ListView Viewallcourse;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_course);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width,height);

        Viewallcourse = (ListView)findViewById(R.id.ViewAllCourse);
        viewbutton = (Button)findViewById(R.id.viewButton);


        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewAllCourse.super.onBackPressed();
            }
        });

        ShowCourse();
    }
    public void ShowCourse(){
        MyDBhandler dBhandler = new MyDBhandler(this);
        List courses = dBhandler.getAllCourseData();

        ArrayAdapter<Course> arrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.activity_list_item,android.R.id.text1, courses);
        Viewallcourse.setAdapter(arrayAdapter);
    }
}
