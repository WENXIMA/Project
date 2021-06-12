package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
  RadioGroup Group;
  RadioButton student,instructor;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    myDBhandler = new MyDBhandler(this);
  }
  private void initView() { //initial button and lines
    username = (EditText)findViewById(R.id.editTextUsername);
    password = (EditText)findViewById(R.id.editTextPassword);
    Login = (Button)findViewById(R.id.btnlogin);
    SignUp = (Button)findViewById(R.id.btn_signup);
    Login.setOnClickListener(this);
    SignUp.setOnClickListener(this);
    Group = findViewById(R.id.loginradiogroup);
    student = findViewById(R.id.radiobutton_student);
    instructor = findViewById(R.id.radiobutton_Instructor);
  }

  public void onClick(View view) {
    switch (view.getId()){
      case R.id.btn_signup: //if click on signup button, turn to register page
        startActivity(new Intent(this, Register.class));
        finish();
        break;
      case R.id.btnlogin:// if click on log-in button, get username,password and all other info
        String name= username.getText().toString().trim(); //get user name
        String password1 = password.getText().toString().trim();//get password

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password1) && student.isChecked()){// check user is student or other type
          ArrayList<Student> data = myDBhandler.AllStudents();
          boolean match = false;
          for(int i=0;i<data.size();i++){
            Student student1 = data.get(i);
            if(name.equals(student1.getUsername()) && password1.equals(student1.getPassword())){//check username and password for student
              match = true;//if all correct, true statement
              break;
            } else {
              match = false;
            }
          }
          if (match){
            Toast.makeText(this,"Log-In Successfully",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Student.class);//if all correct, it will lead us to student page
            startActivity(intent);
            finish();
          } else{
            Toast.makeText(this,"Username/password incorrectly",Toast.LENGTH_SHORT).show();
          }
        } else if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password1) && Instructor.isChecked()){
          ArrayList<Instructor> data = myDBhandler.AllInstructor();
          boolean match = false;
          for(int i=0;i<data.size();i++){
            Instructor instructor1 = data.get(i);
            if(name.equals(instructor1.getUsername()) && password1.equals(instructor1.getPassword())){//check username and password for Instructor
              match = true;
              break;
            } else {
              match = false;
            }
          }
          if (match){
            Toast.makeText(this,"Log-In Successfully",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Student.class);//if all correct, it will lead us to Instructor page
            startActivity(intent);
            finish();
          } else{
            Toast.makeText(this,"Username/password incorrectly",Toast.LENGTH_SHORT).show();
          }
      } else {
          Toast.makeText(this,"Please enter your username and password",Toast.LENGTH_SHORT).show(); // if not correct, it will stay in main page
        } break;
  }
  }

}
