package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCourses extends AppCompatActivity {

    TextView searchCourseHeaderText, searchCoursePromptText, courseCodeLabel, courseNameLabel,
            courseIDLabel, courseIDTextView, warningTextSearchCourse;
    EditText editTextCourseCode, editTextCourseName;
    Button searchButton, editCourseButton, deleteCourseButton;
    public static String editTextCourseCodee;
    public static String editTextCourseNamee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_courses);

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


    public void newCourse (View view) {
        MyDBhandler dbHandler = new MyDBhandler(this);

        String name=editTextCourseName.getText().toString();
        String code=editTextCourseCode.getText().toString();
        name.trim();
        code.trim();

        warningTextSearchCourse.setText(""); // reset warning text in case it was previously triggered

        if (!name.equals("")&& !code.equals("")) {
            Course product = new Course(name, code);
            dbHandler.addCourse(product);

            // clear the text boxes
            editTextCourseName.setText("");
            editTextCourseCode.setText("");
            warningTextSearchCourse.setText("Course added!");

        }else{
            warningTextSearchCourse.setText("Course must have name and code");

        }

    }

    // onClick for the search button
    public void searchCourse(View v){
        MyDBhandler dbHandler = new MyDBhandler(this);
        String courseCodeEntered = editTextCourseCode.getText().toString().trim();
        editTextCourseCodee= editTextCourseCode.getText().toString();
        editTextCourseNamee= editTextCourseName.getText().toString();

        warningTextSearchCourse.setText(""); // reset warning text in case it was previously triggered

        Course course = dbHandler.findCourseAdmin(courseCodeEntered);

        if(course != null){ // if found the course, display its information
            editTextCourseName.setText(String.valueOf(course.getCourseName()));
            editTextCourseCode.setText(String.valueOf(course.getCourseCode()));
            courseIDTextView.setText(String.valueOf(course.getId()));

        } else { // if course not found
            warningTextSearchCourse.setText("Course not found, re-enter course code or name");
        }
    }

    // onClick for the edit button
    public void editCourse(View v){
        MyDBhandler dbHandler = new MyDBhandler(this);
        String courseCodeEntered = editTextCourseCode.getText().toString().trim();
        String courseNameEntered = editTextCourseName.getText().toString().trim();

        warningTextSearchCourse.setText(""); // reset warning text in case it was previously triggered

        if(courseCodeEntered.equals("") || courseNameEntered.equals("")){ // one or both fields left empty
            warningTextSearchCourse.setText("Course name and course code fields cannot be empty");

        } else if(dbHandler.findCourseAdmin(courseCodeEntered) == null){ // if course does not exist
            warningTextSearchCourse.setText("Course does not exist, cannot edit information");

        } else {
            int courseIdDisplayed = Integer.parseInt(courseIDTextView.getText().toString());
            dbHandler.editCourse(courseCodeEntered, courseNameEntered, courseIdDisplayed);
        }
    }

    // onClick for the delete button
    public void deleteCourse(View v){
        MyDBhandler dbHandler = new MyDBhandler(this);
        String courseCodeEntered = editTextCourseCode.getText().toString().trim();

        warningTextSearchCourse.setText(""); // reset warning text in case it was previously triggered

        boolean result = dbHandler.deleteCourse(courseCodeEntered);

        if(result){
            warningTextSearchCourse.setText("Course deleted");
            editTextCourseCode.setText("");
            editTextCourseName.setText("");
            courseIDTextView.setText("");

        } else {
            warningTextSearchCourse.setText("unable to delete course, re-enter course code or name");
        }
    }
}
