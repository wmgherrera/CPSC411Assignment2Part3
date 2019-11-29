package com.herrera.william.ass2part3take2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return StudentDB.getOurInstance().getStudents().size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        View row_view;
        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.student_row, viewGroup, false);
        }else row_view = view;

        Student s = StudentDB.getOurInstance().getStudents().get(i);
        TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
        firstNameView.setText(s.getFirstName());
        TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
        lastNameView.setText(s.getLastName());
        TextView CWID = (TextView) row_view.findViewById(R.id.CWID);
        CWID.setText(s.getCWID());

        row_view.setTag(new Integer(i));

        row_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), StudentDetail.class);
                        intent.putExtra("StudentIndex", (Integer)view.getTag());
                        view.getContext().startActivity(intent);
                    }
                }
        );


        return row_view;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return StudentDB.getOurInstance().getStudents().get(i);
    }

}
