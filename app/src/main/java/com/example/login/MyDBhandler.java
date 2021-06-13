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
    private static final String COLUMN_COURSE_CAPACITY = "capacity";// course capacity
    private static final String COLUMN_COURSE_INSTRUCTOR = "instructor"; // course instructor
    private static final String COLUMN_USERTYPE = "usertype";
    private static SQLiteDatabase db;

    public MyDBhandler(Context context) {
        super(context, "db_test.db", null, 1);
        db = getWritableDatabase();
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

        String CREATE_COURSES_TABLE = "create table " + TABLE_COURSES + "(" + COLUMN_COURSE_CODE + "String primary key not null," + COLUMN_COURSE_NAME + "TEXT not null,"
                + COLUMN_COURSE_CAPACITY + "TEXT," +  COLUMN_COURSE_INSTRUCTOR + " TEXT )";
        db.execSQL(CREATE_COURSES_TABLE);
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
        //SQLiteDatabase db = this.getWritableDatabase();
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
        //SQLiteDatabase db = this.getWritableDatabase();
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
        //SQLiteDatabase db = this.getWritableDatabase();

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
        //SQLiteDatabase db = this.getWritableDatabase();

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

    public ArrayList<User> AllUsers() {
        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user", null, null, null, null, null, "name DESC");
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name, password));
        }
        return list;
    }

    public void addCourse(Course course)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_NAME, course.getCourseName());
        values.put(COLUMN_COURSE_CODE, course.getCourseCode());
        values.put(COLUMN_COURSE_CAPACITY,course.getCapacity());
        values.put(COLUMN_COURSE_INSTRUCTOR,course.getInstructorName());
        db.insert(TABLE_COURSES,null,values);
        db.close();
    }

    public boolean deleteCourse(String courseName)
    {
        boolean result = false;

        String query = "SELECT * FROM" + TABLE_COURSES + "WHERE" + COLUMN_COURSE_NAME + "=\"" + courseName + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String idSTR = cursor.getString(0);
            db.delete(TABLE_COURSES, COLUMN_COURSE_CODE+ " = " + idSTR, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}