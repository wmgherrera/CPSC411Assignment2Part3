package com.herrera.william.ass2part3take2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected ListView mStudentList;
    protected Menu mMenu;
    protected MainAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create DB if it does not already exist
        File dbFile = this.getDatabasePath("Student.db");
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID Text)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Course (CourseID Text, Grade Text, CWID Text)" );
        createStudents(db);
        populateOurInstanceFromDB(db);
        db.close();



        setContentView(R.layout.activity_main);
        mStudentList = findViewById(R.id.student_listview_id);
        adapter = new MainAdapter();
        mStudentList.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        mMenu = menu;
        menu.findItem(R.id.action_add).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        adapter.notifyDataSetChanged();
        super.onStart();
    }

    public void createStudents(SQLiteDatabase db){
        db.execSQL("INSERT INTO Student VALUES ('James', 'Shen', '667448555')");
        db.execSQL("INSERT INTO Course VALUES ('CPSC 411', 'A+', '667448555')");

        db.execSQL("INSERT INTO Student VALUES ('Will', 'Herrera', '8675309')");
        db.execSQL("INSERT INTO Course VALUES ('CPSC 464', 'D+', '8675309')");
    }

    public void populateOurInstanceFromDB(SQLiteDatabase db)
    {
        ArrayList<Student> students = new ArrayList<Student>();

        // For loop to add students to students array list
        Cursor cursor1 = db.query("Student",null, null, null, null,null,null);
        if (cursor1.getCount() > 0){
            while (cursor1.moveToNext()) {
                // create student object from first item in the DB
                Student s = new Student(
                        cursor1.getString(cursor1.getColumnIndex("FirstName")),
                        cursor1.getString(cursor1.getColumnIndex("LastName")),
                        cursor1.getString(cursor1.getColumnIndex("CWID")));

                ArrayList<Course> courses = new ArrayList<Course>();

                Cursor cursor2 = db.query("Course",null, null, null, null,null,null);
                if (cursor2.getCount() > 0){
                    while (cursor2.moveToNext()) {
                        String matchingCWID = cursor2.getString(cursor2.getColumnIndex("CWID"));
                        if (s.getCWID().equals(matchingCWID))
                        {
                            Course course = new Course(
                                    (cursor2.getString(cursor2.getColumnIndex("CourseID"))),
                                    (cursor2.getString(cursor2.getColumnIndex("Grade"))),
                                    (cursor2.getString(cursor2.getColumnIndex("CWID"))));
                            courses.add(course);
                        }

                    }
                }
                s.setCourses(courses);
                students.add(s);
            }
        }
        // Set new values
        StudentDB.getOurInstance().setStudents(students);
    }
}
