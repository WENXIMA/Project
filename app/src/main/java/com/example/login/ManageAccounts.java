package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

public class ManageAccounts extends AppCompatActivity {
    TextView warningTextSearchUsername1;
    EditText deleteusername,usertype;
    Button back, del;

    public static String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accounts);
        deleteusername= findViewById(R.id.editTextUserName);
        usertype=findViewById(R.id.editTextUsertype);
        back = findViewById(R.id.Return);
        del=findViewById(R.id.delbutton);
        warningTextSearchUsername1=findViewById(R.id.warningTextSearchCourse);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageAccounts.this,AdminWelcome.class));
            }
        });
    }

    public void findaccount (View view){
        MyDBhandler dBhandler = new MyDBhandler(ManageAccounts.this);

        String name=deleteusername.getText().toString().trim();
        type=usertype.getText().toString().trim();
        warningTextSearchUsername1.setText("");
        User user = dBhandler.findusers(name);
        System.out.println(user);

        if (user != null){
            deleteusername.setText(String.valueOf(user.getUsername()));
            usertype.setText(String.valueOf(user.getUserType()));


        } else{
            warningTextSearchUsername1.setText("USER NOT EXIST, PLEASE RE-ENTER USER NAME");
        }


    }
    public void deleteAccount(View view){
        MyDBhandler dBhandler = new MyDBhandler(this);
        String delete = deleteusername.getText().toString().trim();
        warningTextSearchUsername1.setText("");
        boolean result = dBhandler.deleteAccount(delete);
        if(result){
            warningTextSearchUsername1.setText("Account delete");
            deleteusername.setText("");
            usertype.setText("");
        } else{
            warningTextSearchUsername1.setText("UNABLE TO DELETE ACCOUNT, RE-ENTER USERNAME");
        }

    }
}