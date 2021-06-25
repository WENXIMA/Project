package com.example.login;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;

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
    public void assignCourseToInstructor() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);

        // create a course to test
        ActivityScenario<AdminCourses> adminCoursesScenario = ActivityScenario.launch(AdminCourses.class);
        onView(withId(R.id.editTextCourseCode)).perform(typeText("TC1"));
        onView(withId(R.id.editTextCourseName)).perform(typeText("Test Course"), closeSoftKeyboard());
        onView(withId(R.id.add)).perform(click());
        adminCoursesScenario.close();

        // create test instructor account
        ActivityScenario<Register> registerScenario = ActivityScenario.launch(Register.class);
        onView(withId(R.id.editTextUsername)).perform(typeText("testInstructor"));
        onView(withId(R.id.editTextPassword)).perform(typeText("TI1"), closeSoftKeyboard());
        onView(withId(R.id.instructorSelect)).perform(click());
        onView(withId(R.id.createButton)).perform(click());
        registerScenario.close();

        // assign course
        ActivityScenario<InstructorSearchAssign> instructorSearchAssignScenario = ActivityScenario.launch(InstructorSearchAssign.class);
        onView(withId(R.id.editTextCourseCode)).perform(typeText("TC1"), closeSoftKeyboard());
        onView(withId(R.id.assign)).perform(scrollTo(), click());
        instructorSearchAssignScenario.close();

        // check for successful assignment
        Course assignedCourse = db.findCourseInstructor("TC1");
        assertEquals("testInstructor", assignedCourse.getInstructor());
    }
}
