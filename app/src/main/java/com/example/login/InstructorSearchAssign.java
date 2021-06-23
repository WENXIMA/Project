package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class InstructorSearchAssign extends AppCompatActivity {
    TextView searchCourseHeaderText, searchCoursePromptText, courseCodeLabel, courseNameLabel,
            courseIDLabel, courseIDTextView, warningTextSearchCourse;
    EditText editTextCourseCode, editTextCourseName;
    Button searchButton, editCourseButton, deleteCourseButton;
    public static String editTextCourseCodee;
    public static String editTextCourseNamee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_search_assign);
        searchCourseHeaderText = findViewById(R.id.searchCourseHeaderText);
        searchCoursePromptText = findViewById(R.id.searchCoursePromptText);

        courseCodeLabel = findViewById(R.id.courseCodeLabel);
        editTextCourseCode = findViewById(R.id.editTextCourseCode);

        courseNameLabel = findViewById(R.id.courseNameLabel);
        editTextCourseName = findViewById(R.id.editTextCourseName);

        courseIDLabel = findViewById(R.id.courseIDLabel);
        courseIDTextView = findViewById(R.id.courseIDTextView);

        warningTextSearchCourse = findViewById(R.id.warningTextSearchCourse);

        searchButton = findViewById(R.id.searchButton);
        editCourseButton = findViewById(R.id.editCourseButton);
        deleteCourseButton = findViewById(R.id.deleteCourseButton);
    }

    public boolean searchCourse(View v){

        MyDBhandler dbHandler = new MyDBhandler(this);
        String courseCodeEntered = editTextCourseCode.getText().toString().trim();
        editTextCourseCodee= editTextCourseCode.getText().toString();
        editTextCourseNamee= editTextCourseName.getText().toString();

        warningTextSearchCourse.setText(""); // reset warning text in case it was previously triggered

        Course course = dbHandler.findCourseInstructor(courseCodeEntered);

        if(course != null){ // if found the course, display its information
            editTextCourseName.setText(String.valueOf(course.getCourseName()));
            editTextCourseCode.setText(String.valueOf(course.getCourseCode()));
            courseIDTextView.setText(String.valueOf(course.getId()));
            return true;

        } else { // if course not found
            warningTextSearchCourse.setText("Course not found, re-enter course code or name");
            return false;
        }

    }

    public void assign(View v){
        MyDBhandler dbHandler = new MyDBhandler(this);
        String courseCodeEntered = editTextCourseCode.getText().toString().trim();
        warningTextSearchCourse.setText("");

        if (searchCourse(v)){
            Course course = dbHandler.findCourseInstructor(courseCodeEntered);
            System.out.println(course.getInstructor());
            if (course.getInstructor()==null||course.getInstructor().equals("")){
                course.setInstructor(MainActivity.user.getUsername());
                dbHandler.assign(course,MainActivity.user.getUsername());
                System.out.println(course.getInstructor());
                warningTextSearchCourse.setText("Now you are assigned to the course");
            }else if (course.getInstructor().equals(MainActivity.user.getUsername())){
                warningTextSearchCourse.setText("You are already assigned to this course");
            }
            else {
                warningTextSearchCourse.setText("Course is already assigned to "+course.getInstructor());
            }
        }else{
            warningTextSearchCourse.setText("Course not found, re-enter course code or name");
        }
    }

    public void unassign(View v){
        MyDBhandler dbHandler = new MyDBhandler(this);
        String courseCodeEntered = editTextCourseCode.getText().toString().trim();
        warningTextSearchCourse.setText("");

        if (searchCourse(v)){
            Course course = dbHandler.findCourseInstructor(courseCodeEntered);
            if (course.getInstructor().equals(MainActivity.user.getUsername())){
                dbHandler.unassign(course);
                course.setInstructor(null);
                course.setCapacity(null);
                course.setDays(null);
                course.setHours(null);
                course.setDescription(null);
                warningTextSearchCourse.setText("Now you are unassigned from the course");
            }else {
                warningTextSearchCourse.setText("Can't unassign a course that you are not assigned to.");
            }
        }else{
            warningTextSearchCourse.setText("Course not found, re-enter course code or name");
        }
    }



}