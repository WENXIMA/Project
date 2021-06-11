package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class AddCourse extends AppCompatActivity {
    TextView idView;
    EditText courseName;
    EditText courseCode;

    ListView productlist;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        idView = (TextView) findViewById(R.id.productID);
        courseName = (EditText) findViewById(R.id.courseName);
        courseCode = (EditText) findViewById(R.id.courseCode);
        productlist = (ListView) findViewById(R.id.productListView);

        MyDBCourseHandler dbHandler = new MyDBCourseHandler(this);
        listItem = new ArrayList<>();

        // call the viewData() method to display the existing products
//        viewData();

        productlist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String text = productlist.getItemAtPosition(i).toString();
                Toast.makeText(AddCourse.this, ""+text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // we use onClick for the Add button in our layout to call this method
    public void newProduct (View view) {
        MyDBCourseHandler dbHandler = new MyDBCourseHandler(this);

        String name=courseName.getText().toString();
        String code=courseCode.getText().toString();
        name.trim();
        code.trim();

        if (!name.equals("")&& !code.equals("")) {
            Course product = new Course(name, code);
            dbHandler.addProduct(product);

            // clear the text boxes
            courseName.setText("");
            courseCode.setText("");
            idView.setText("Course added!");

            listItem.clear();
//            viewData();

        }else{
            idView.setText("Course must have name and code");

        }

    }


//    private void viewData(){
//        MyDBCourseHandler dbHandler = new MyDBCourseHandler(this);
//
//        // call the viewData() method in MyDBHandler that runs the query
//        Cursor cursor = dbHandler.viewData();
//
//        // if there are no products in the table a toast says there is no data to show
//        // otherwise, while there are products, keep moving to the next product
//        if(cursor.getCount() == 0){
//            Toast.makeText(this, "Not data to show", Toast.LENGTH_SHORT).show();
//        }else{
//            while(cursor.moveToNext()){
//                // I just want to display the product name so I only get column 1 from the table row
//                listItem.add(cursor.getString(1));
//            }
//            // create an array adapter that provides a view for each item
//            // simple_list_item_1 is a built-in xml layout from Android
//            // I decided to use this instead of creating my own .xml file for items of the ListView
//            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
//
//            // attaching the adapter to the ListView
//            productlist.setAdapter(adapter);
//        }
//    }

}