package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    TextView createAccountHeaderText, usernameLabel, passwordLabel, accountSelectLabel, warningText;
    EditText editTextUsername, editTextPassword;
    RadioGroup accountSelectGroup;
    RadioButton instructorSelect, studentSelect;
    Button cancelButton, createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        createAccountHeaderText = findViewById(R.id.createAccountHeaderText);

        usernameLabel = findViewById(R.id.usernameLabel);
        editTextUsername = findViewById(R.id.editTextUsername);

        passwordLabel = findViewById(R.id.passwordLabel);
        editTextPassword = findViewById(R.id.editTextPassword);

        accountSelectLabel = findViewById(R.id.accountSelectLabel);
        accountSelectGroup = findViewById(R.id.accountSelectGroup);
        instructorSelect = findViewById(R.id.instructorSelect);
        studentSelect = findViewById(R.id.studentSelect);

        warningText = findViewById(R.id.warningText);

        cancelButton = findViewById(R.id.cancelButton);
        createButton = findViewById(R.id.createButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,MainActivity.class));
            }
        });
    }

    // onClick for the create button, bottom right
    public void createNewAccount(View v){
        MyDBhandler dbHandler = new MyDBhandler(this);
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
//        dbHandler.addAdmin();

        warningText.setText(""); // reset warning text in case it was previously triggered

        if(instructorSelect.isChecked() || studentSelect.isChecked()){ // user chose an account type
            if(username.matches("") || password.matches("")){
                warningText.setText("Please enter a username and password to proceed!");

            } else if(instructorSelect.isChecked()){ // user/pass fields not empty & chose an instructor account
                Instructor instructor = new Instructor(username, password, "instructor");
                dbHandler.addInstructor(instructor);

                // clear editTexts
                editTextUsername.setText("");
                editTextPassword.setText("");

                System.out.println(instructor+"account type will be: instructor");

            } else if(studentSelect.isChecked()){ // user/pass fields not empty & chose a student account
                Student student = new Student(username, password, "student");
                dbHandler.addStudent(student);

                // clear editTexts
                editTextUsername.setText("");
                editTextPassword.setText("");

                System.out.println(student+"account type will be: student");
            }
        } else if(!instructorSelect.isChecked() && !studentSelect.isChecked()) { // user has not chosen an account type
            warningText.setText("Please select an account type to proceed!");
        }
    }

    // onClick for the cancel button, bottom left
    public void cancelAccountCreation(){
        // return to the login (activity_main)

    }
}
