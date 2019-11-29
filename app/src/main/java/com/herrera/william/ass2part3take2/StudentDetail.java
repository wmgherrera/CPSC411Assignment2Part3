package com.herrera.william.ass2part3take2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDetail extends AppCompatActivity {

    LinearLayout parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        parentView = findViewById(R.id.parent_view);

        int studentIndex = getIntent().getIntExtra("StudentIndex", 0);

        Student studentObj = StudentDB.getOurInstance().getStudents().get(studentIndex);
        EditText firstName = findViewById(R.id.first_name);
        EditText lastName = findViewById(R.id.last_name);
        EditText CWID = findViewById(R.id.CWID);
        firstName.setText(studentObj.getFirstName());
        lastName.setText(studentObj.getLastName());
        CWID.setText(studentObj.getCWID());

        for(int i = 0; i < studentObj.mCourses.size(); i++)
        {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            TextView classText = new TextView(this);
            classText.setText("Class:");
            classText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            ll.addView(classText);

            TextView classValue = new TextView(this);
            classValue.setText(studentObj.getCourses().get(i).getCourseID());
            classValue.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            ll.addView(classValue);

            TextView gradeText = new TextView(this);
            gradeText.setText("Grade:");
            gradeText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            ll.addView(gradeText);

            TextView gradeValue = new TextView(this);
            gradeValue.setText(studentObj.getCourses().get(i).getGrade());
            gradeValue.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            ll.addView(gradeValue);

            parentView.addView(ll);
        }
    }

}
