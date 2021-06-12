package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBCourseHandler extends SQLiteOpenHelper {
    //defining the schema
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CourseDB.db";
    private static final String TABLE_COURSE = "Course";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_COURSENAME = "coursename";
    private static final String COLUMN_COURSECODE = "coursecode";

    // constructor
    public MyDBCourseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // create the table
    @Override
    public void onCreate(SQLiteDatabase db){
        // CREATE TABLE TABLE_COURSE (COLUMN_ID INTEGER PRIMARY KEY, COLUMN_COURSENAME TEXT,
        // COLUMN_COURSECODE DOUBLE)
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_COURSE
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_COURSENAME + " TEXT,"
                + COLUMN_COURSECODE + " DOUBLE" +
                ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    // deletes old tables and creates a new one
    // change tables by incrementing the database version number
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        onCreate(db);
    }

    // insert into database
    public void addProduct(Course course){
        SQLiteDatabase db = this.getWritableDatabase();

        // creating an empty set of values
        ContentValues values = new ContentValues();
        // add values to the set
        values.put(COLUMN_COURSENAME, course.getCourseName());
        values.put(COLUMN_COURSECODE, course.getCourseCode());

        // insert the set into the products table and close
        db.insert(TABLE_COURSE, null, values);
        db.close();
    }
//    public Cursor viewData(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT * FROM " + TABLE_COURSE;
//
//        // passing the query
//        Cursor cursor = db.rawQuery(query, null);
//
//        // returns all products from table
//        return cursor;
//    }


}
