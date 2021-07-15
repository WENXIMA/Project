package com.example.login;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class TestCases {

    @Test
    public void searchForClassByDay() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);
        Course course= new Course("name","code");
        course.setDays("monday");
        db.addCourse(course);

        Course temp=db.findCourse("","","monday");

        assertEquals("code",temp.getCourseCode());
        assertEquals("name",temp.getCourseName());
        assertEquals("monday",temp.getDays());
    }

    @Test
    public void searchForClassByCode() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);
        Course course= new Course("name1","code1");
        course.setDays("tuesday");
        db.addCourse(course);

        Course temp=db.findCourse("code1","","");
        assertEquals("code1",temp.getCourseCode());
        assertEquals("name1",temp.getCourseName());
        assertEquals("tuesday",temp.getDays());
    }

    @Test
    public void searchForClassByName() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);
        Course course= new Course("name2","code2");
        course.setDays("wednesday");
        db.addCourse(course);

        Course temp=db.findCourse("","name2","");
        assertEquals("code2",temp.getCourseCode());
        assertEquals("name2",temp.getCourseName());
        assertEquals("wednesday",temp.getDays());
    }

    @Test
    public void Enroll(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);

        Course course= new Course("name3","code3");
        course.setDays("thursday");
        course.setHours("11-12");
        db.addCourse(course);

        Student student = new Student("Tom","Key","student");

        db.enrollCourse(course, student.getUsername());

        List<Course> coursesExpected= new LinkedList<>();
        coursesExpected.add(course);

        System.out.println(db.findCoursesByStudent(student.getUsername()));
        assertEquals(coursesExpected,db.findCoursesByStudent(student.getUsername()));
    }

    @Test
    public void Unenroll(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);

        Course course= new Course("name4","code4");
        course.setDays("friday");
        course.setHours("11-12");
        db.addCourse(course);

        Student student = new Student("Key1","Key1","student");

        db.enrollCourse(course, student.getUsername());

        List<Course> coursesExpected= new LinkedList<>();
        coursesExpected.add(course);

        assertNotEquals(coursesExpected,db.findCoursesByStudent(student.getUsername()));
    }
}
