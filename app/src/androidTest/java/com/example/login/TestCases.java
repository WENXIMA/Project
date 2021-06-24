package com.example.login;

import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
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
    public void assignUnassignCourseToInstructor() {
        // start on login page (activity_main)
        // login as admin
        onView(withId(R.id.userName)).perform(typeText("admin"));
        onView(withId(R.id.userpassword)).perform(typeText("admin123"));

        // create a course to test
        onView(withId(R.id.Courses)).perform(click());
        onView(withId(R.id.editTextCourseCode)).perform(typeText("TC1"));
        onView(withId(R.id.editTextCourseName)).perform(typeText("Test Course"));
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.Return)).perform(click()); // back to admin welcome
        onView(withId(R.id.LogOut)).perform(click()); // log out of admin account

        onView(withId(R.id.register)).perform(click());

        // create test instructor account
        onView(withId(R.id.editTextUsername)).perform(typeText("testInstructor"));
        onView(withId(R.id.editTextPassword)).perform(typeText("TI1"));
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.cancelButton)).perform(click()); // back to login page

        // log into instructor account
        onView(withId(R.id.userName)).perform(typeText("testInstructor"));
        onView(withId(R.id.userpassword)).perform(typeText("TI1"));
        onView(withId(R.id.login)).perform(click());

        onView(withId(R.id.assign)).perform(click()); // instructor welcome page

        // assign course
        onView(withId(R.id.editTextCourseCode)).perform(typeText("TC1"));
        onView(withId(R.id.assign)).perform(click());

        // check for successful assignment


        // unassign course

        // check for successful unassignment

    }
}
