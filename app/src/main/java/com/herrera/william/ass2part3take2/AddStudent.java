package com.herrera.william.ass2part3take2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudent extends AppCompatActivity {

    GridLayout mGridLayout;
    LinearLayout parentView;
    EditText firstName;
    EditText lastName;
    EditText CWID;
    EditText newClass;
    EditText grade;
    ArrayList<Course> courses;
    boolean needToSaveNewClass = false;
    private static String DB_PATH = "/data/data/com.herrera.william.ass2part3take2/databases/";
    private static String DB_NAME = "Student.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        parentView = findViewById(R.id.parent_view);
        mGridLayout = findViewById(R.id.grid_layout);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        CWID = findViewById(R.id.cwid_text);


        courses = new ArrayList<>();

        final Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addView();
            }
        });


    }

    public void addView()
    {
        if(needToSaveNewClass == true)
        {
            courses.add(new Course(newClass.getText().toString(), grade.getText().toString(), CWID.getText().toString()));
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
            ContentValues vals = new ContentValues();
            vals.put("CourseID", newClass.getText().toString());
            vals.put("Grade", grade.getText().toString());
            vals.put("CWID", CWID.getText().toString());
            db.insert("Course", null, vals);
            db.close();
            newClass = null;
            grade = null;
            needToSaveNewClass = false;
            addView();
        }
        else
        {
            needToSaveNewClass = true;
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            TextView classText = new TextView(this);
            classText.setText("Class:");
            classText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            ll.addView(classText);

            newClass = new EditText(this);
            newClass.setText("");
            newClass.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            ll.addView(newClass);

            TextView gradeText = new TextView(this);
            gradeText.setText("Grade:");
            gradeText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            ll.addView(gradeText);

            grade = new EditText(this);
            grade.setText("");
            grade.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            ll.addView(grade);

            parentView.addView(ll);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            // Update student array list
            ArrayList<Student> studentArrayList = StudentDB.getOurInstance().getStudents();
            Student student = new Student(firstName.getText().toString(), lastName.getText().toString(), CWID.getText().toString());
            courses.add(new Course(newClass.getText().toString(), grade.getText().toString(), CWID.getText().toString()));
            student.setCourses(courses);
            studentArrayList.add(student);
            StudentDB.getOurInstance().setStudents(studentArrayList);

            //update database with new student
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
            ContentValues studentVals = new ContentValues();
            studentVals.put("FirstName", firstName.getText().toString());
            studentVals.put("LastName", lastName.getText().toString());
            studentVals.put("CWID", CWID.getText().toString());
            db.insert("Student", null, studentVals);

            // update database with new course
            ContentValues courseVals = new ContentValues();
            courseVals.put("CourseID", newClass.getText().toString());
            courseVals.put("Grade", grade.getText().toString());
            courseVals.put("CWID", CWID.getText().toString());
            db.insert("Course", null, courseVals);
            db.close();

        }
        return super.onKeyDown(keyCode, event);
    }

}
