package com.herrera.william.ass2part3take2;

public class Course {

    protected String mCourseID;
    protected String mGrade;
    protected String mCWID;

    public Course(String courseID, String grade, String CWID) {
        mCourseID = courseID;
        mGrade = grade;
        mCWID = CWID;
    }

    public String getCourseID() {
        return mCourseID;
    }

    public void setCourseID(String courseID) {
        mCourseID = courseID;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public String getCWID() {
        return mCWID;
    }

    public void setCWID(String CWID) {
        mCWID = CWID;
    }
}
