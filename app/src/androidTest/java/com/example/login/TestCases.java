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
    // check that course assignment by instructor is reflected in database
    public void assignCourseToInstructor() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);

        // create a course to test
        ActivityScenario<AdminCourses> adminCoursesScenario = ActivityScenario.launch(AdminCourses.class);
        onView(withId(R.id.editTextCourseCode)).perform(typeText("TC1"));
        onView(withId(R.id.editTextCourseName)).perform(typeText("Test Course"), closeSoftKeyboard());
        onView(withId(R.id.add)).perform(click());
        adminCoursesScenario.close();

        // assign course
        ActivityScenario<InstructorSearchAssign> instructorSearchAssignScenario = ActivityScenario.launch(InstructorSearchAssign.class);
        onView(withId(R.id.editTextCourseCode)).perform(typeText("TC1"), closeSoftKeyboard());
        // force set username because shortcutting through the views doesn't set one (technically not logged in)
        MainActivity.user = new Instructor("testInstructor", "TI1", "instructor");
        onView(withId(R.id.assign)).perform(scrollTo(), click());

        // check for successful assignment in database
        Course assignedCourse = db.findCourseInstructor("TC1");
        assertEquals("testInstructor", assignedCourse.getInstructor());
    }

    @Test
    // if user leaves username and/or password fields empty in registration
    public void emptyRegistrationField(){
        ActivityScenario<Register> registerScenario = ActivityScenario.launch(Register.class);
        onView(withId(R.id.studentSelect)).perform(click());
        onView(withId(R.id.createButton)).perform(click());
        onView(withText("Please enter a username and password to proceed!")).check(matches(isDisplayed()));
    }

    @Test
    // if course doesn't exist and the admin tries to search for it
    public void courseSearchUnsuccessful(){
        ActivityScenario<AdminCourses> adminCoursesScenario = ActivityScenario.launch(AdminCourses.class);
        onView(withId(R.id.editTextCourseCode)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("Course not found, re-enter course code or name")).check(matches(isDisplayed()));
    }

    @Test
    // check that course name and course code edits by admin are reflected in database
    public void adminEditCourse(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);

        Course testCourse = new Course("Test Course 2", "TC2");
        db.addCourse(testCourse);

        ActivityScenario<AdminCourses> adminCoursesScenario = ActivityScenario.launch(AdminCourses.class);

        // search for course
        onView(withId(R.id.editTextCourseCode)).perform(typeText("TC2"), closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());

        // edit course name and course code
        onView(withId(R.id.editTextCourseCode)).perform(clearText());
        onView(withId(R.id.editTextCourseName)).perform(clearText());
        onView(withId(R.id.editTextCourseCode)).perform(typeText("TC3"));
        onView(withId(R.id.editTextCourseName)).perform(typeText("Test Course 3"), closeSoftKeyboard());
        onView(withId(R.id.editCourseButton)).perform(click());

        AdminCourses.editTextCourseCodee = "TC3";
        AdminCourses.editTextCourseNamee = "Test Course 3";
        Course updatedCourse = db.findCourseAdmin("TC3");
        assertEquals("TC3", updatedCourse.getCourseCode());
        assertEquals("Test Course 3", updatedCourse.getCourseName());
    }

    @Test
    // check that account deletion by admin is reflected in database
    public void adminDeleteAccount(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);

        // create an instructor via DBhandler
        Instructor testInstructor = new Instructor("testInstructor2", "TI2", "instructor");
        db.addInstructor(testInstructor);

        // delete instructor using UI
        ActivityScenario<ManageAccounts> manageAccountsScenario = ActivityScenario.launch(ManageAccounts.class);
        onView(withId(R.id.editTextUserName)).perform(typeText("testInstructor2"), closeSoftKeyboard());
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.delbutton)).perform(click());

        assertTrue(db.findusers("testInstructor2") == null);
    }
}
