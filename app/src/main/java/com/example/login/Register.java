package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    private MyDBhandler db;

    TextView createAccountHeaderText, usernameLabel, passwordLabel, accountSelectLabel;
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

        cancelButton = findViewById(R.id.cancelButton);
        createButton = findViewById(R.id.createButton);
    }

    // user chose one of the account options (radio buttons)
    public void onClickAccountSelect(View v){

    }

    // user clicked create button at the bottom right
    public void onClickCreateButton(View v){
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

    }

    // user canceled their account creation
    public void onClickCancelButton(){

    }
}
