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
        String sql_admin = "create table administractor(" +
                " account varchar(20)," +
                " password varchar(20))";
        String sql_insert_admin = "insert into administractor values('admin123','admin123')";

        db.execSQL(sql_admin);
        db.execSQL(sql_insert_admin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
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

    // add an student account to the database
    public void addStudent(Student student) {
        // create a new map of values where column names are keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, student.getUsername());
        values.put(COLUMN_PASSWORD, student.getPassword());

        // insert into table and close
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }
    //Find Student in database
    public Student findStudent(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM" + TABLE_STUDENTS + "WHERE" + COLUMN_USERNAME + "=\"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        Student student2 = new Student();
        if (cursor.moveToFirst()) {
            //user.setID(Integer.parseInt(cursor.getString(0)));
            student2.setUsername(cursor.getString(0));
            student2.setPassword(cursor.getString(1));
            student2.setUserType(cursor.getString(2));
            cursor.close();
        } else {
            student2 = null;
        }
        db.close();
        return student2;
    }
    //Find Instructor in database
    public Instructor findInstructor(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM" + TABLE_INSTRUCTORS + "WHERE" + COLUMN_USERNAME + "=\"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        Instructor instructor = new Instructor();
        if (cursor.moveToFirst()) {
            //user.setID(Integer.parseInt(cursor.getString(0)));
            instructor.setUsername(cursor.getString(0));
            instructor.setPassword(cursor.getString(1));
            instructor.setUserType(cursor.getString(2));
            cursor.close();
        } else {
            instructor = null;
        }
        db.close();
        return instructor;
    }



    // delete an student account to the database
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
    // delete an instructor account to the database
    public boolean deleteinstructors(String username) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM" + TABLE_INSTRUCTORS + "WHERE" + COLUMN_USERNAME + "=\"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String idSTR = cursor.getString(0);
            db.delete(TABLE_INSTRUCTORS, COLUMN_ID + " = " + idSTR, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    //List all students in database
    public ArrayList<Student> AllStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TABLE_STUDENTS;
        Cursor cursor = db.rawQuery(query,null);

        ArrayList<Student> students = new ArrayList<>();

        while(cursor.moveToNext()) {
            /*
            String temp = "Username: " + cursor.getString(0).toString() + " " +
                    "Password: " + cursor.getString(1).toString();
            students.add(temp);
             */
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String UserType = cursor.getString(cursor.getColumnIndex("UserType"));
            students.add(new Student(name,password));
        }
        cursor.close();
        db.close();

        return students;
    }
    //List all Instructors in database
    public ArrayList<Instructor> AllInstructor() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TABLE_INSTRUCTORS;
        Cursor cursor = db.rawQuery(query,null);

        ArrayList<Instructor> Instructor = new ArrayList<>();

        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String UserType = cursor.getString(cursor.getColumnIndex("UserType"));
            Instructor.add(new Instructor(name,password));
        }
        cursor.close();
        db.close();

        return Instructor;
    }

}
