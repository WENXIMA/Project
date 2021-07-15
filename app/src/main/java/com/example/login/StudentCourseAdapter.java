package com.example.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

public class StudentCourseAdapter extends BaseAdapter {
    private LinkedList<Course> dataList;
    private Context context;

    public StudentCourseAdapter(LinkedList<Course> courses, Context context)
    {
        this.dataList = courses;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Course getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataList.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.student_listitem_course_enrolled,viewGroup,false);
        TextView courseName = (TextView)view.findViewById(R.id.studentListItemCourseName);
        Button button = (Button)view.findViewById(R.id.StudentListButton);
        TextView courseHours = view.findViewById(R.id.studentListItemCourseHours);
        TextView courseDays = view.findViewById(R.id.studentListItemCourseDays);

        courseName.setText(dataList.get(position).getCourseName());

        String tempH = dataList.get(position).getHours();
        if( tempH == null || tempH.equals(""))
        {
            courseHours.setText("Not Set");
        }
        else
            courseHours.setText(dataList.get(position).getHours());

        String tempD = dataList.get(position).getDays();
        if( tempD == null || tempD.equals(""))
        {
            courseDays.setText("Not Set");
        }
        else
            courseDays.setText(dataList.get(position).getDays());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,StudentCourseDetail.class);
                intent.putExtra("id",dataList.get(position).getId());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

        return view;
    }
}
