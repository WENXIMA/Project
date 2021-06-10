package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  //TextView idView;
  EditText username;
  EditText password;
  RadioButton Instructor;
  RadioButton Student;
  Button Login;
  Button SignUp;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    username = (EditText)findViewById(R.id.editTextUsername);
    password = (EditText)findViewById(R.id.editTextPassword);
    Login = (Button)findViewById(R.id.btn_login);
    SignUp = (Button)findViewById(R.id.btn_signup);
    Login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,Register.class));
      }
    });

  }
/*
  public void NewUser(View view){
    MyDBhandler dBhandler = new MyDBhandler(this);
    User user = new User(username.getText().toString(), password.getText().toString());
    dBhandler.addUser(user);
    username.setText("");
    password.setText("");
  }

  public void lookupUser(View view){
    MyDBhandler dBhandler = new MyDBhandler(this);
    User user = dBhandler.findUser(username.getText().toString());
    if(user != null){
      password.setText(String.valueOf(user.getPassword()));
    } else {

    }
  }

 */
}