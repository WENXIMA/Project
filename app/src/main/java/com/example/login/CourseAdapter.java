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

public class CourseAdapter extends BaseAdapter {

    private LinkedList<Course> dataList;
    private Context context;

    public CourseAdapter(LinkedList<Course> courses, Context context)
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
        view = LayoutInflater.from(context).inflate(R.layout.listitem_course_manage,viewGroup,false);
        TextView courseName = (TextView)view.findViewById(R.id.ListItemCourseName);
        TextView courseDes = (TextView)view.findViewById(R.id.listItemDescription);
        Button button = (Button)view.findViewById(R.id.ListItemButton);
        Button returnPre = view.findViewById(R.id.ListReturn);

        courseName.setText(dataList.get(position).getCourseName());

        String temp = dataList.get(position).getDescription();
        if( temp == null || temp.equals(""))
        {
            courseDes.setText("No Description");
        }
        else
            courseDes.setText(dataList.get(position).getDescription());

//        String[] days,hours;
//        String tempd,temph;
//        tempd = dataList.get(position).getDays();
//        temph = dataList.get(position).getHours();
//
//        String temp = "";

//        if(tempd != null && temph != null)
//        {
//            days = dataList.get(position).getDays().split(",");
//            hours = dataList.get(position).getHours().split(",");
//
//            for(int i = 0;i < days.length;i++)
//            {
//                temp += days[i];
//                temp += "\t";
//                temp += hours[i];
//                temp+="\n";
//            }
//        }
//        else
//        {
//            temp = "No time set";
//        }
//        courseTime.setText(temp);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,InstractorUpdateCourseDetails.class);
                intent.putExtra("id",dataList.get(position).getId());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

        return view;
    }
}
