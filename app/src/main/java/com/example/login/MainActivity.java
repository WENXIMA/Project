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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  //TextView idView;
  private MyDBhandler myDBhandler;
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
    initView();
    myDBhandler = new MyDBhandler(this);
  }
  private void initView() {
    username = (EditText)findViewById(R.id.editTextUsername);
    password = (EditText)findViewById(R.id.editTextPassword);
    Login = (Button)findViewById(R.id.btn_login);
    SignUp = (Button)findViewById(R.id.btn_signup);
    Login.setOnClickListener(this);
    SignUp.setOnClickListener(this);
  }

  public void onClick(View view) {
    switch (view.getId()){
      case R.id.btn_signup:
        startActivity(new Intent(this, Register.class));
        //finish();
        break;
      case R.id.btn_login:
        String name= username.getText().toString().trim();
        String password1 = password.getText().toString().trim();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password1)){
          ArrayList<User> data = myDBhandler.AllUsers();
          boolean match = false;
          for(int i=0;i<data.size();i++){
            User user = data.get(i);
            if(name.equals(user.getUsername()) && password1.equals(user.getPassword())){
              match = true;
              break;
            } else {
              match = false;
            }
          }
          if (match){
            Toast.makeText(this,"Log-In Successfully",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
          } else{
            Toast.makeText(this,"Username/password incorrectly",Toast.LENGTH_SHORT).show();
          }
        } else {
          Toast.makeText(this,"Please enter your username and password",Toast.LENGTH_SHORT).show();
        } break;

  }
  }

}