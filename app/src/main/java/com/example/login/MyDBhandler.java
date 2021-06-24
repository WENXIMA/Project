package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBhandler extends SQLiteOpenHelper {

    // tables
    private static final String TABLE_INSTRUCTORS = "instructors";
    private static final String TABLE_STUDENTS = "students";
    private static final String TABLE_COURSES = "courses";
    private static final String TABLE_USER = "user";

    // columns
    private static final int DATABASE_VERSION = 37;
    private static final String DATABASE_NAME = "productDB.db";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_TYPE = "type";

    private static final String COLUMN_COURSE_CODE = "courseCode"; // for course table
    private static final String COLUMN_COURSE_NAME = "courseName"; // for course table
    private static final String COLUMN_INSTRUCTOR ="instructor"; //for course table
    private static final String COLUMN_DAYS = "courseDays";
    private static final String COLUMN_HOURS = "courseHours";
    private static final String COLUMN_DESCRIPTION = "courseDescription";
    private static final String COLUMN_CAPACITY = "courseCapacity";


    private static SQLiteDatabase db;
    public MyDBhandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the table for users
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_USER
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_TYPE + " TEXT" +
                ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);


        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_COURSE_CODE + " TEXT,"
                + COLUMN_COURSE_NAME + " TEXT,"
                + COLUMN_INSTRUCTOR + " TEXT,"
                + COLUMN_DAYS + " TEXT,"
                + COLUMN_HOURS + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_CAPACITY + " TEXT"
                + ")";

        db.execSQL(CREATE_COURSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        onCreate(db);
    }

    public  ArrayList<User> getAllDATA(){
        ArrayList<User> list = new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_USER,null);
        if(cursor.moveToFirst()){
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            list.add(new User(username,password,type));
        }

        while(cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            list.add(new User(username,password,type));
        }

        System.out.println(list);

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
            String instructor = cursor.getString(cursor.getColumnIndex("instructor"));

            list.add(new Course(code,name,id,instructor));

            while(cursor.moveToNext()){
                code = cursor.getString(cursor.getColumnIndex("courseCode"));
                name = cursor.getString(cursor.getColumnIndex("courseName"));
                id = cursor.getString(cursor.getColumnIndex("_id"));
                instructor = cursor.getString(cursor.getColumnIndex("instructor"));

                list.add(new Course(code,name,id,instructor));
            }
        }
        cursor.close();
        db.close();
        return list;
    }

    //ADDED CODE
    public User findusers(String username){
        SQLiteDatabase db = this.getWritableDatabase();

        // run a query to find the course
        // SELECT * FROM TABLE_COURSES WHERE COLUMN_COURSE_CODE = courseCode
        ArrayList<User> list= getAllDATA();
        User temp=null;
        for(int i=0;i<list.size(); i++){
            temp= list.get(i);
            if (temp.getUsername().equals(username)&& temp.getUserType().equals(ManageAccounts.type)){
                return temp;
            }
        }
        if (username.equals("")||ManageAccounts.type.equals("")) {
            for (int i = 0; i < list.size(); i++) {
                temp = list.get(i);
                if (temp.getUsername().equals(username) || temp.getUserType().equals(ManageAccounts.type)) {
                    return temp;
                }
            }
        }else {
            return null;
        }
        return null;
    }
    public boolean deleteAccount(String username){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        // run a query to find the course
        // SELECT * FROM TABLE_COURSES WHERE COLUMN_COURSE_CODE = courseCode
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME +
                " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);

        // delete the course from the db
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_USER, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    // add an instructor account to the database
    public void addInstructor(Instructor instructor){
        // create a new map of values where column names are keys
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        System.out.println(instructor.getUsername()+" "+instructor.getPassword()+" "+instructor.getUserType());
        values.put(COLUMN_USERNAME, instructor.getUsername());
        values.put(COLUMN_PASSWORD, instructor.getPassword());
        values.put(COLUMN_TYPE, instructor.getUserType());

        // insert into table and close
        db.insert(TABLE_USER, null, values);
        db.close();
        System.out.println(getAllDATA());

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
        System.out.println(getAllDATA());
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
//        System.out.println(getAllDATA());
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
        System.out.println(getAllCourseData());

    }

    // search for a course
    public Course findCourseInstructor(String courseCode){

        ArrayList<Course> list= getAllCourseData();
        Course temp=null;
        for(int i=0;i<list.size(); i++){
            temp= list.get(i);
            if (temp.getCourseName().equals(InstructorSearchAssign.editTextCourseNamee)&& temp.getCourseCode().equals(InstructorSearchAssign.editTextCourseCodee)){
                return temp;
            }
        }
        for(int i=0;i<list.size(); i++) {
            temp= list.get(i);
            if (InstructorSearchAssign.editTextCourseNamee.equals("")||InstructorSearchAssign.editTextCourseCodee.equals("")) {
                if (temp.getCourseName().equals(InstructorSearchAssign.editTextCourseNamee) || temp.getCourseCode().equals(InstructorSearchAssign.editTextCourseCodee)) {
                    return temp;
                }
            }else {
                return null;
            }
        }
        return null;

    }

    public Course findCourseAdmin(String courseCode){

        ArrayList<Course> list= getAllCourseData();
        Course temp=null;
        for(int i=0;i<list.size(); i++){
            temp= list.get(i);
            if (temp.getCourseName().equals(AdminCourses.editTextCourseNamee)&& temp.getCourseCode().equals(AdminCourses.editTextCourseCodee)){
                return temp;
            }
        }
        for(int i=0;i<list.size(); i++) {
            temp= list.get(i);
            if (AdminCourses.editTextCourseNamee.equals("")||AdminCourses.editTextCourseCodee.equals("")) {
                if (temp.getCourseName().equals(AdminCourses.editTextCourseNamee) || temp.getCourseCode().equals(AdminCourses.editTextCourseCodee)) {
                    return temp;
                }
            }else {
                return null;
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
        System.out.println(getAllCourseData());
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
        System.out.println(getAllCourseData());
    }

    public void assign(Course course,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        int id = course.getId();

        // run a query to find the course
        // SELECT * FROM TABLE_COURSES WHERE COLUMN_COURSE_CODE = courseCode
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_ID +
                " = \"" + id + "\"";
        Cursor cursor = db.rawQuery(query, null);

        // edit the course in the db
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);

            ContentValues updatedColumns = new ContentValues();
            updatedColumns.put(COLUMN_INSTRUCTOR, name);

            db.update(TABLE_COURSES, updatedColumns, COLUMN_ID + " = " + idStr, null);
            cursor.close();
        }
    }

    public void unassign(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        int id = course.getId();

        // run a query to find the course
        // SELECT * FROM TABLE_COURSES WHERE COLUMN_COURSE_CODE = courseCode
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_ID +
                " = \"" + id + "\"";
        Cursor cursor = db.rawQuery(query, null);

        // edit the course in the db
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);

            ContentValues updatedColumns = new ContentValues();
            updatedColumns.put(COLUMN_INSTRUCTOR, "");
            updatedColumns.put(COLUMN_DAYS,"");
            updatedColumns.put(COLUMN_HOURS,"");
            updatedColumns.put(COLUMN_DESCRIPTION,"");
            updatedColumns.put(COLUMN_CAPACITY,"");
            db.update(TABLE_COURSES, updatedColumns, COLUMN_ID + " = " + idStr, null);
            cursor.close();
        }
    }


}