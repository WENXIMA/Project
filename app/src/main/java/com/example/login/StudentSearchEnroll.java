package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentSearchEnroll extends AppCompatActivity {
    TextView searchCourseHeaderText, searchCoursePromptText, courseCodeLabel, courseNameLabel, dayLabel, courseIDLabel, courseIDTextView, warningTextSearchCourse;
    EditText editTextCourseCode, editTextCourseName, editTextDay;
    Button searchButton, enroll, unenroll, returnToWelcomePage;

    Course course;

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

    public boolean StudentSearchCourse(View v){
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

    public boolean StudentSearchCourseTwo(){
        MyDBhandler dbHandler = new MyDBhandler(this);
        String courseCodeEntered = editTextCourseCode.getText().toString().trim();
        String courseNameEntered = editTextCourseName.getText().toString().trim();
        String dayEntered = editTextDay.getText().toString().toLowerCase().trim();

        warningTextSearchCourse.setText(""); // reset warning text in case it was previously triggered

        course = dbHandler.findCourse(courseCodeEntered, courseNameEntered, dayEntered);

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

    public void StudentEnrollCourse(View v) {
        MyDBhandler dBhandler = new MyDBhandler(this);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        if(StudentSearchCourseTwo())
        {
            List<Course> temp = dBhandler.findCoursesByStudent(MainActivity.user.getUsername());
            if(!find(temp,course))
            {
                String tempCD = course.getDays();
                String tempCH = course.getHours();
                if(tempCD != null && tempCH != null)
                {
                    String[] currD = tempCD.split(",");
                    String[] currH = tempCH.split(",");
                    for(int i = 0;i < temp.size();i++)
                    {
                        String D = temp.get(i).getDays();
                        String H = temp.get(i).getHours();
                        if(D == null || H == null )
                            continue;
                        String[] tempD = D.split(",");
                        String[] tempH = H.split(",");

                        for(int j = 0; j < currD.length; j++)
                        {
                            String currStart = (currH[j].split("-")[0]);
                            String currEnd = currH[j].split("-")[1];
                            if(j > tempD.length)
                                continue;
                            for(int k = 0; k < tempD.length;k++)
                            {
                                if(currD[j].equals(tempD[k])) {
                                    try {
                                        Date tempCurrStart = format.parse(currStart);
                                        Date tempCurrEnd = format.parse(currEnd);
                                        Date tempStart = format.parse(tempH[k].split("-")[0]);
                                        Date tempEnd = format.parse(tempH[k].split("-")[1]);
//                                    (J_S.compareTo(I_S) == -1 && I_S.compareTo(J_E) == -1)
//                                        || (J_S.compareTo(I_E) == -1 && I_E.compareTo(J_E) == -1)
//　　　　　　　　　　　　       || (I_S.compareTo(J_S) == -1 && J_S.compareTo(I_E) == -1)
//　　　　　　　　　　　　       || (I_S.compareTo(J_E) == -1 && J_E.compareTo(I_E) == -1)
//                          || J_E.compareTo(I_S) == 0 || J_S.compareTo(I_E) == 0
//                                            || J_E.compareTo(I_E) == 0 || J_S.compareTo(I_S) == 0)
                                        if (tempCurrStart.before(tempStart) && tempCurrEnd.after(tempStart) && tempCurrEnd.before(tempEnd) ||
                                                tempCurrStart.before(tempStart) && tempCurrEnd.after(tempEnd) ||
                                                tempCurrStart.after(tempStart) && tempCurrStart.before(tempEnd) && tempCurrEnd.after(tempStart) && tempCurrEnd.after(tempEnd) ||
                                                tempCurrStart.after(tempStart) && tempCurrStart.before(tempEnd) && tempCurrEnd.after(tempEnd)) {
                                            warningTextSearchCourse.setText("Time conflict with " + temp.get(i).getCourseName());
                                            return;
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
                dBhandler.enrollCourse(course,MainActivity.user.getUsername());
                warningTextSearchCourse.setText("Enroll successfully");
            }
            else
                warningTextSearchCourse.setText("You are already in the course");
        }
        else {
            warningTextSearchCourse.setText("Course not found, re-enter course code or name");
        }

    }

    public void StudentUnenrollCourse(View v) {
        MyDBhandler dbhandler = new MyDBhandler(this);
        String courseCodeEntered = editTextCourseCode.getText().toString().trim();
        String courseNameEntered = editTextCourseName.getText().toString().trim();
        String dayEntered = editTextDay.getText().toString().toLowerCase().trim();

        warningTextSearchCourse.setText(""); // reset warning text in case it was previously triggered

        course=dbhandler.findCourse(courseCodeEntered,courseNameEntered,dayEntered);

        if(StudentSearchCourseTwo())
        {
            List<Course> temp = dbhandler.findCoursesByStudent(MainActivity.user.getUsername());
            if(find(temp,course))
            {
                dbhandler.dropCourse(course,MainActivity.user.getUsername());
                warningTextSearchCourse.setText("Unenrolled successfully");
            }
            else
                warningTextSearchCourse.setText("You are already not in the course");
        }
        else {
            warningTextSearchCourse.setText("Course not found, re-enter course code or name");
        }

    }

    private boolean find(List<Course> courseList,Course course)
    {
        int courseID = course.getId();
        for(int i = 0; i < courseList.size();i++)
        {
            if(courseID == courseList.get(i).getId())
                return true;
        }
        return false;
    }
}
