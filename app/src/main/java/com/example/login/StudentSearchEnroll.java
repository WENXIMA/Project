package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StudentSearchEnroll extends AppCompatActivity {
    TextView warningTextSearchCourse,courseIDTextView;
    EditText editTextCourseCode, editTextCourseName, editTextDay;
    Button searchButton, Enroll;
    public static String editTextCourseCodee;
    public static String editTextCourseNamee;
    Course course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_enroll);

        editTextCourseCode = findViewById(R.id.studentEditTextCourseCode);

        editTextCourseName = findViewById(R.id.studentEditTextCourseName);

        warningTextSearchCourse = findViewById(R.id.StudentWarningTextSearchCourse);

        searchButton = findViewById(R.id.StudentSearchButton);

        editTextDay = findViewById(R.id.editTextDay);

        courseIDTextView = findViewById(R.id.courseIDTextView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentSearchCourse();
            }
        });

        Enroll = findViewById(R.id.enroll);

        Enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentEnrollCourse();
            }
        });
    }

    public boolean StudentSearchCourse(){
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

    public void StudentEnrollCourse()
    {
        MyDBhandler dBhandler = new MyDBhandler(this);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        if(StudentSearchCourse())
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
                        String[] tempD = temp.get(i).getDays().split(",");
                        String[] tempH = temp.get(i).getHours().split(",");

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


