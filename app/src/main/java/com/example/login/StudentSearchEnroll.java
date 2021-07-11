package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentSearchEnroll extends AppCompatActivity {
    TextView searchCourseHeaderText, searchCoursePromptText, courseCodeLabel, courseNameLabel, dayLabel, courseIDLabel, courseIDTextView, warningTextSearchCourse;
    EditText editTextCourseCode, editTextCourseName, editTextDay;
    Button searchButton, enroll, unenroll, returnToWelcomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search_enroll);

        searchCourseHeaderText = findViewById(R.id.searchCourseHeaderText);
        searchCoursePromptText = findViewById(R.id.searchCoursePromptText);

        courseCodeLabel = findViewById(R.id.courseCodeLabel);
        editTextCourseCode = findViewById(R.id.editTextCourseCode);

        courseNameLabel = findViewById(R.id.courseNameLabel);
        editTextCourseName = findViewById(R.id.editTextCourseName);

        dayLabel = findViewById(R.id.dayLabel);
        editTextDay = findViewById(R.id.editTextDay);

        courseIDLabel = findViewById(R.id.courseIDLabel);
        courseIDTextView = findViewById(R.id.courseIDTextView);

        warningTextSearchCourse = findViewById(R.id.warningTextSearchCourse);

        searchButton = findViewById(R.id.searchButton);
        enroll = findViewById(R.id.enroll);
        unenroll = findViewById(R.id.unenroll);
        returnToWelcomePage = findViewById(R.id.returnToWelcomePage);

        returnToWelcomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentSearchEnroll.this, StudentWelcome.class));
            }
        });
    }

    public boolean studentSearchCourse(View v){
        MyDBhandler dbHandler = new MyDBhandler(this);
        String courseCodeEntered = editTextCourseCode.getText().toString().trim();
        String courseNameEntered = editTextCourseName.getText().toString().trim();
        String dayEntered = editTextDay.getText().toString().toLowerCase().trim();

        warningTextSearchCourse.setText(""); // reset warning text in case it was previously triggered

        Course course = dbHandler.findCourse(courseCodeEntered, courseNameEntered, dayEntered);

        if(course != null){ // if found the course, display its information
            editTextCourseName.setText(String.valueOf(course.getCourseName()));
            editTextCourseCode.setText(String.valueOf(course.getCourseCode()));
            editTextDay.setText(String.valueOf(course.getDays()));
            courseIDTextView.setText(String.valueOf(course.getId()));
            return true;

        } else { // if course not found
            warningTextSearchCourse.setText("Course not found, re-enter course code or name");
            return false;
        }
    }
}
