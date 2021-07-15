package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorUpdateCourseDetails extends AppCompatActivity {

    private int id;
    private Course course;
    private MyDBhandler myDBhandler;

    TextView  currentDes, currentCapa, currentDays, currentHours, currentName;
    EditText days, hours, des, capa;
    Button returnPre, update, unassign, viewStudents;
    String instructor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_course_details);

        id = getIntent().getIntExtra("id", -1);

        myDBhandler = new MyDBhandler(this);

        course = myDBhandler.findCourseByID(id);
        instructor = course.getInstructor();

        update = (Button)findViewById(R.id.detailUpdate);
        unassign = findViewById(R.id.detailUnassign);
        viewStudents = findViewById(R.id.viewStudents);

        currentName = (TextView)findViewById(R.id.courseDetailsPrompt);

        currentName.setText("Course name: " + course.getCourseName());

        currentCapa = (TextView)findViewById(R.id.detailCurrentCapacityLabel);
        capa = (EditText)findViewById(R.id.detailEditCourseCap);

        currentDays = (TextView)findViewById(R.id.detailCurrentCourseDaysLabel);
        days = (EditText)findViewById(R.id.detailEditCourseDays);

        currentHours = (TextView)findViewById(R.id.detailCurrentCourseHoursLabel);
        hours = (EditText)findViewById(R.id.detailEditCourseHours);

        currentDes = (TextView)findViewById(R.id.detailCurrentDescriptionLabel);
        des = (EditText)findViewById(R.id.detailEditCourseDes);

        returnPre = findViewById(R.id.ReturnPre);

        viewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(InstructorUpdateCourseDetails.this, InstructorViewStudents.class);
                temp.putExtra("courseCode", course.getCourseCode());
                temp.putExtra("courseName", course.getCourseName());
                startActivity(temp);
                finish();
            }
        });

        returnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temp = new Intent(InstructorUpdateCourseDetails.this, InstructorCourseManage.class);
                temp.putExtra("name", instructor);
                startActivity(temp);
                finish();
            }
        });

        if (course.getCapacity() == null || course.getCapacity().equals(""))
            currentCapa.setText("Not Set");
        else
            currentCapa.setText(course.getCapacity());

        if (course.getDescription() == null || course.getDescription().equals(""))
            currentDes.setText("No Description");
        else
            currentDes.setText(course.getDescription());

        if (course.getDays() == null || course.getDays().equals(""))
            currentDays.setText("Not Set");
        else
            currentDays.setText(course.getDays());

        if (course.getHours() == null || course.getHours().equals(""))
            currentHours.setText("Not Set");
        else
            currentHours.setText(course.getHours());
    }

    public void editUpdate(View view) {
        String tempD, tempH, tempDe, tempC;
        tempC = capa.getText().toString().trim();
        tempD = days.getText().toString().toLowerCase().trim();
        tempDe = des.getText().toString().trim();
        tempH = hours.getText().toString().trim();

        if (tempC.equals("")) {
            tempC = course.getCapacity();}

        if (tempD.equals("")) {
            tempD = course.getDays();
        }

        if (tempDe.equals("")) {
            tempDe = course.getDescription();
        }

        if (tempH.equals("")) {
            tempH = course.getHours();
        }

        myDBhandler.updateCourseDetails(course, tempDe, tempD, tempH, tempC);
        Toast.makeText(this, "Course details update successfully", Toast.LENGTH_SHORT).show();
    }

    public void Unassign(View view){
        myDBhandler.unassign(course);
        Toast.makeText(this, "You are unassigned successfully", Toast.LENGTH_SHORT).show();
        Intent temp = new Intent(InstructorUpdateCourseDetails.this, InstructorCourseManage.class);
        temp.putExtra("name", instructor);
        startActivity(temp);
        finish();
    }
}
