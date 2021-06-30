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

        // assign course
        ActivityScenario<InstructorSearchAssign> instructorSearchAssignScenario = ActivityScenario.launch(InstructorSearchAssign.class);
        onView(withId(R.id.editTextCourseCode)).perform(typeText("TC1"), closeSoftKeyboard());
        // force set username because shortcutting through the views doesn't set one (technically not logged in)
        MainActivity.user = new Instructor("testInstructor", "TI1", "instructor");
        onView(withId(R.id.assign)).perform(scrollTo(), click());

        // check for successful assignment message
        //onView(withText("Now you are assigned to the course")).check(matches(isDisplayed()));

        //instructorSearchAssignScenario.close();

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
    public void editCourseDetails(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDBhandler db = new MyDBhandler(appContext);

        // create instructor
        Instructor testInstructor = new Instructor("testInstructor", "TI1", "instructor");
        db.addInstructor(testInstructor);

        // create course
        Course testCourse = new Course("Test Course 2", "TC2");
        db.addCourse(testCourse);

        // assign course to use course editing feature
        db.assign(testCourse, "testInstructor");

        ActivityScenario<InstractorUpdateCourseDetails> editCourseDetailsScenario = ActivityScenario.launch(InstractorUpdateCourseDetails.class);

        onView(withId(R.id.detailEditCourseDes)).perform(typeText("test description"), closeSoftKeyboard());
        onView(withId(R.id.detailEditCourseDays)).perform(typeText("Mon, Wed"), closeSoftKeyboard());
        onView(withId(R.id.detailEditCourseHours)).perform(typeText("8:30am-9:50am, 8:30am-9:50am"), closeSoftKeyboard());
        onView(withId(R.id.detailEditCourseCap)).perform(typeText("78"), closeSoftKeyboard());
        onView(withId(R.id.detailUpdate)).perform(click());

        Course updatedCourse = db.findCourseInstructor("TC2");
        assertEquals("test description", updatedCourse.getDescription());
        assertEquals("Mon, Wed", updatedCourse.getDays());
        assertEquals("8:30am-9:50am, 8:30am-9:50am", updatedCourse.getHours());
        assertEquals("78", updatedCourse.getCapacity());
    }
}
