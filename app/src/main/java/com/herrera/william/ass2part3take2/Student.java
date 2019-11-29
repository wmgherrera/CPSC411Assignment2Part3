package com.herrera.william.ass2part3take2;

import java.util.ArrayList;

public class Student {

    protected String mFirstName;
    protected String mLastName;
    protected String mCWID;
    protected ArrayList<Course> mCourses;

    public Student(String firstName, String lastName, String CWID) {
        mFirstName = firstName;
        mLastName = lastName;
        mCWID = CWID;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getCWID() {
        return mCWID;
    }

    public void setCWID(String CWID) {
        mCWID = CWID;
    }

    public ArrayList<Course> getCourses() {
        return mCourses;
    }

    public void setCourses(ArrayList<Course> courses) {
        mCourses = courses;
    }
}
