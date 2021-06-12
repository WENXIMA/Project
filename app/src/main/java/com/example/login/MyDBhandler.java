package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBhandler extends SQLiteOpenHelper {
    // database schema

    // tables
    private static final String TABLE_INSTRUCTORS = "instructors";
    private static final String TABLE_STUDENTS = "students";
    private static final String TABLE_COURSES = "courses";

    // columns
    private static final String COLUMN_ID = "_id"; // for all tables
    private static final String COLUMN_USERNAME = "username"; // for instructor and student tables
    private static final String COLUMN_PASSWORD = "password"; // for instructor and student tables
    private static final String COLUMN_COURSE_CODE = "courseCode"; // for course table
    private static final String COLUMN_COURSE_NAME = "courseName"; // for course table

    private static SQLiteDatabase db;
    public MyDBhandler(Context context){
        super(context,"db_test.db",null,1);
        db=getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the table for users
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"username TEXT,"+"password TEXT)" );

        // create the table for instructors
        String CREATE_INSTRUCTOR_TABLE = "CREATE TABLE " + TABLE_INSTRUCTORS + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD +
                " TEXT" + ")";
        db.execSQL(CREATE_INSTRUCTOR_TABLE);

        // create the table for students
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD +
                " TEXT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
    public void add(String username,String password)
    {
        db.execSQL("INSERT INTO user(username,password)VALUES(?,?)",new Object[]{username,password});
    }
    public static ArrayList<User> getAllDATA(){
        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("username",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(username,password));
        }
        return list;
    }

    // add an instructor account to the database
    public void addInstructor(Instructor instructor){
        // create a new map of values where column names are keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, instructor.getUsername());
        values.put(COLUMN_PASSWORD, instructor.getPassword());

        // insert into table and close
        db.insert(TABLE_INSTRUCTORS, null, values);
        db.close();
    }

    // add a student account to the database
    public void addStudent(Student student){
        // create a new map of values where column names are keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, student.getUsername());
        values.put(COLUMN_PASSWORD, student.getPassword());

        // insert into table and close
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }
}

