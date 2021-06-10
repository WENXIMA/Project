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
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_INSTRUCTORS = "instructors";
    private static final String TABLE_STUDENTS = "students";
    //private static final String TABLE_PRODUCTS = "users";
    private static final String TABLE_COURSES = "courses";

    // columns
    private static final String COLUMN_ID = "_id"; // for all tables
    private static final String COLUMN_USERNAME = "username"; // for instructor and student tables
    private static final String COLUMN_PASSWORD = "password"; // for instructor and student tables
    private static final String COLUMN_COURSE_CODE = "courseCode"; // for course table
    private static final String COLUMN_COURSE_NAME = "courseName"; // for course table
    private static final String COLUMN_USERTYPE = "usertype";
    private static SQLiteDatabase db;

    public MyDBhandler(Context context) {
        super(context, "db_test.db", null, 1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the table for users
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "username TEXT," + "password TEXT)");

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
/*
    public void addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

 */
    public User findStudent(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM" + TABLE_STUDENTS + "WHERE" + COLUMN_USERNAME + "=\"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            //user.setID(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(0));
            user.setPassword(cursor.getString(1));
            user.setUserType(cursor.getString(2));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }
    public User findInstructor(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM" + TABLE_INSTRUCTORS + "WHERE" + COLUMN_USERNAME + "=\"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            //user.setID(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(0));
            user.setPassword(cursor.getString(1));
            user.setUserType(cursor.getString(2));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }


    // add an instructor account to the database
    public void addInstructor(Instructor instructor) {
        // create a new map of values where column names are keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, instructor.getUsername());
        values.put(COLUMN_PASSWORD, instructor.getPassword());

        // insert into table and close
        db.insert(TABLE_INSTRUCTORS, null, values);
        db.close();
    }

    // add a student account to the database
    public void addStudent(Student student) {
        // create a new map of values where column names are keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, student.getUsername());
        values.put(COLUMN_PASSWORD, student.getPassword());

        // insert into table and close
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }
    public boolean deletestudent(String username) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM" + TABLE_STUDENTS + "WHERE" + COLUMN_USERNAME + "=\"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String idSTR = cursor.getString(0);
            db.delete(TABLE_STUDENTS, COLUMN_ID + " = " + idSTR, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean deleteinstructors(String username) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM" + TABLE_INSTRUCTORS + "WHERE" + COLUMN_USERNAME + "=\"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String idSTR = cursor.getString(0);
            db.delete(TABLE_STUDENTS, COLUMN_ID + " = " + idSTR, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public List<String> AllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TABLE_STUDENTS+TABLE_INSTRUCTORS;
        Cursor cursor = db.rawQuery(query, null);
        List<String> users = new ArrayList<String>();
        while (cursor.moveToNext()) {
            String temp = //"ID:" + cursor.getString(0).toString() + " " +
                    "UserName" + cursor.getString(1).toString() + " " +
                            "Password" + cursor.getString(2).toString() + " " +
                            "UserType" + cursor.getString(3).toString() + " ";
            users.add(temp);
        }
        cursor.close();
        db.close();

        return users;
    }
}