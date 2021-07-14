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
import static org.junit.Assert.assertEquals;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class TestCases {

    @Test
    public void searchForClassByDay() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);
        Course course= new Course("code","name","25","Key","Monday");
        db.addCourse(course);

        Course temp=db.findCourse("","","Monday");
        System.out.println(temp.getCourseCode());

        assertEquals("code",temp.getCourseCode());
        assertEquals("name",temp.getCourseName());
        assertEquals("Monday",temp.getDays());
    }

    @Test
    public void searchForClassByCode() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);
        Course course= new Course("code","name","25","Key","Monday");
        db.addCourse(course);

        Course temp=db.findCourse("code","","");
        System.out.println(temp.getCourseCode());
        assertEquals("code",temp.getCourseCode());
        assertEquals("name",temp.getCourseName());
        assertEquals("Monday",temp.getDays());
    }

    @Test
    public void searchForClassByName() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);
        Course course= new Course("code","name","25","Key","Monday");
        db.addCourse(course);

        Course temp=db.findCourse("","name","");
        System.out.println(temp.getCourseCode());
        assertEquals("code",temp.getCourseCode());
        assertEquals("name",temp.getCourseName());
        assertEquals("Monday",temp.getDays());
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
