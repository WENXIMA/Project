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
    public static ArrayList<User> allUsers= new ArrayList<User>();

    // tables
    private static final String TABLE_INSTRUCTORS = "instructors";
    private static final String TABLE_STUDENTS = "students";
    private static final String TABLE_COURSES = "courses";
    private static final String TABLE_USER = "user";

    // columns
    private static final int DATABASE_VERSION = 27;
    private static final String DATABASE_NAME = "productDB.db";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_COURSE_CODE = "courseCode"; // for course table
    private static final String COLUMN_COURSE_NAME = "courseName"; // for course table

    private static SQLiteDatabase db;
    private static SQLiteDatabase db1;
    public MyDBhandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the table for users
//        db.execSQL("CREATE TABLE IF NOT EXISTS user(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"username TEXT,"+"password TEXT)" );

        // create the table for instructors

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_USER
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_TYPE + " TEXT" +
                ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        // create the table for students
//        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "(" + COLUMN_ID +
//                " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT,"+ COLUMN_TYPE + " TEXT," + COLUMN_PASSWORD +
//                " TEXT" + ")";
//        db.execSQL(CREATE_STUDENT_TABLE);

        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_COURSE_CODE + " TEXT,"
                + COLUMN_COURSE_NAME + " TEXT" +
                ")";

        System.out.println("test");
        db.execSQL(CREATE_COURSES_TABLE);
//        addAdmin();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }
//    public void add(String username,String password)
//    {
//        db.execSQL("INSERT INTO user(username,password)VALUES(?,?)",new Object[]{username,password});
//    }


    public  ArrayList<User> getAllDATA(){
        ArrayList<User> list = new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_USER,null);
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            list.add(new User(username,password,type));
        }

        return list;
    }

    public ArrayList<Course> getAllCourseData(){
        ArrayList<Course> list = new ArrayList<Course>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_COURSES,null);
        if(cursor.moveToFirst()){
            String code = cursor.getString(cursor.getColumnIndex("courseCode"));
            String name = cursor.getString(cursor.getColumnIndex("courseName"));
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            list.add(new Course(code,name,id));

            while(cursor.moveToNext()){
                code = cursor.getString(cursor.getColumnIndex("courseCode"));
                name = cursor.getString(cursor.getColumnIndex("courseName"));
                id = cursor.getString(cursor.getColumnIndex("_id"));
                list.add(new Course(code,name,id));
            }
        }


        System.out.println(list.size()+" _____________");
        return list;
    }


    // add an instructor account to the database
    public void addInstructor(Instructor instructor){
        // create a new map of values where column names are keys
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        System.out.println(instructor.getUsername()+" "+instructor.getPassword()+" "+instructor.getUserType());
        values.put(COLUMN_USERNAME, instructor.getUsername());
        values.put(COLUMN_PASSWORD, instructor.getPassword());
        values.put(COLUMN_TYPE, instructor.getUserType());

        // insert into table and close
        db.insert(TABLE_USER, null, values);
        db.close();

        allUsers.add(instructor);
    }

    // add a student account to the database
    public void addStudent(Student student){
        // create a new map of values where column names are keys
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, student.getUsername());
        values.put(COLUMN_PASSWORD, student.getPassword());
        values.put(COLUMN_TYPE, student.getUserType());
        // insert into table and close
        db.insert(TABLE_USER, null, values);
        db.close();
        allUsers.add(student);
    }

    public void addAdmin(){
        SQLiteDatabase db = this.getWritableDatabase();
        User admin=new User("admin","admin123","admin");

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, admin.getUsername());
        values.put(COLUMN_PASSWORD, admin.getPassword());
        values.put(COLUMN_TYPE, admin.getUserType());
        // insert into table and close
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    //Courses
    public void addCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();

        // creating an empty set of values
        ContentValues values = new ContentValues();
        // add values to the set
        values.put(COLUMN_COURSE_NAME, course.getCourseName());
        values.put(COLUMN_COURSE_CODE, course.getCourseCode());

        // insert the set into the products table and close
        db.insert(TABLE_COURSES, null, values);
        db.close();
    }

    // search for a course
    public Course findCourse(String courseCode){

        ArrayList<Course> list= getAllCourseData();
        Course temp=null;
        for(int i=0;i<list.size(); i++){
            temp= list.get(i);
            if (temp.getCourseName().equals(AdminPage.editTextCourseNamee)&& temp.getCourseCode().equals(AdminPage.editTextCourseCodee)){
                return temp;
            }else if (temp.getCourseName().equals(AdminPage.editTextCourseNamee)|| temp.getCourseCode().equals(AdminPage.editTextCourseCodee)){
                return temp;
            }
        }
        return null;

    }

    public Course findCourseByID(int id){
                SQLiteDatabase db = this.getWritableDatabase();


        // run a query to find the course
        // SELECT * FROM TABLE_COURSES WHERE COLUMN_COURSE_CODE = courseCode
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_ID +
                " = \"" + id + "\"";
        Cursor cursor = db.rawQuery(query, null);

        // create an object and get the result
        Course course = new Course();
        if(cursor.moveToFirst()){
            course.setId(Integer.parseInt(cursor.getString(0)));
            course.setCourseCode(cursor.getString(1));
            course.setCourseName(cursor.getString(2));
            cursor.close();
        } else {
            course = null;
        }
        db.close();
        return course;
    }

    // delete a course
    public boolean deleteCourse(String courseCode){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        // run a query to find the course
        // SELECT * FROM TABLE_COURSES WHERE COLUMN_COURSE_CODE = courseCode
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_COURSE_CODE +
                " = \"" + courseCode + "\"";
        Cursor cursor = db.rawQuery(query, null);

        // delete the course from the db
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_COURSES, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    // edit a course
    public void editCourse(String courseCode, String courseName, int id){
        SQLiteDatabase db = this.getWritableDatabase();

        // run a query to find the course
        // SELECT * FROM TABLE_COURSES WHERE COLUMN_COURSE_CODE = courseCode
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_ID +
                " = \"" + id + "\"";
        Cursor cursor = db.rawQuery(query, null);

        // edit the course in the db
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);

            ContentValues updatedColumns = new ContentValues();
            updatedColumns.put(COLUMN_COURSE_CODE, courseCode);
            updatedColumns.put(COLUMN_COURSE_NAME, courseName);

            db.update(TABLE_COURSES, updatedColumns, COLUMN_ID + " = " + idStr, null);
            cursor.close();
        }
    }


}

