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

//    @Test
//    public void adminEditCourse(){
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        MyDBhandler db = new MyDBhandler(appContext);
//
//    }
//
//    @Test
//    public void adminDeleteAccount(){
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        MyDBhandler db = new MyDBhandler(appContext);
//        assertTrue(db.findusers("testInstructor2") == null);
//    }
}
