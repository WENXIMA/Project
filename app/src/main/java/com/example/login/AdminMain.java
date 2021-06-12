package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminMain extends AppCompatActivity {

    TextView greeting;
    Button btn_add,btn_search,btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main_page);

        greeting = (TextView)findViewById(R.id.greetingView);
        btn_search = (Button)findViewById(R.id.search);
        btn_add = (Button)findViewById(R.id.add);
        btn_delete = (Button)findViewById(R.id.delete);

        Greeting();
    }

    private void Greeting()
    {
        String str = "Welcome " +  CurrentUser.firstName + "/" + CurrentUser.username + " ! You are logged in as " +CurrentUser.userType + ".";

        greeting.setText(str);
    }

    private void SearchCourse(View view)
    {

    }

    private void AddCourse(View view)
    {

    }

    private void Delete(View view)
    {

    }



}
