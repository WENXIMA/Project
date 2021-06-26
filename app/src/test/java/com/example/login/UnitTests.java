package com.example.login;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTests {
    @Test
    public void assignCourseToInstructor() {
        //Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        InstructorSearchAssign dbContext = new InstructorSearchAssign();
        MyDBhandler db = new MyDBhandler(dbContext);

        // create an instructor
        Instructor testInstructor = new Instructor("testInstructor", "TI1", "instructor");
        db.addInstructor(testInstructor);

        // create a course
        Course testCourse = new Course("testCourse", "TC1");
        db.addCourse(testCourse);

        // assign course
        db.assign(testCourse, testInstructor.getUsername());

        // check for successful assignment
        Course assignedCourse = db.findCourseInstructor("TC1");
        assertEquals("testInstructor", assignedCourse.getInstructor());
    }
}
