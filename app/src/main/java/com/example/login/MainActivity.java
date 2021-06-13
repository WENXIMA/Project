package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyDBhandler DB;
    public EditText username;
    public EditText password1;
    private Button login;
    private Button register;
    private User view;
    public static User user=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        username = findViewById(R.id.userName);
        password1 = findViewById(R.id.userpassword);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this,Register.class);
                startActivity(intent5);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = password1.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    MyDBhandler db=new MyDBhandler(MainActivity.this);
                    db.addAdmin();
                    ArrayList<User> data = db.getAllDATA();
                    System.out.println(data);

                    boolean userdata = false;
                    for (int i = 0; i < data.size(); i++) {
                        user= data.get(i);

                        if (name.equals(user.getUsername()) && password.equals(user.getPassword())) {
                            userdata = true;
                            break;
                        } else {
                            userdata = false;
                        }
                    }

                    System.out.println(user);
                    if (userdata && user.getUserType().equals("instructor")) {
                        System.out.println("INSTRUCTOR");
                        openInstructorWelcome(user.getUsername());

                    }else if (userdata && user.getUserType().equals("student")) {
                        openStudentWelcome(user.getUsername());

                    }else if (userdata && user.getUserType().equals("admin")) {
                        openAdminWelcome(user.getUsername());

                    }
                    else {
                        Toast.makeText(MainActivity.this, "Username/Password incorrectly", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Username/password cannot be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
        DB = new MyDBhandler(MainActivity.this);
    }
    public void openInstructorWelcome(String username) {
        Intent intent = new Intent(this, InstructorWelcome.class);
        startActivity(intent);
    }

    public void openStudentWelcome(String username) {
        Intent intent = new Intent(this, StudentWelcome.class);
        startActivity(intent);
    }

    public void openAdminWelcome(String username) {
        Intent intent = new Intent(this, AdminPage.class);
        startActivity(intent);
    }



}



