package com.tolstenkov.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class StudentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Student> students;

    public StudentAdapter(Context context, ArrayList<Student> students){
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup  parent) {
        if(convertView == null){
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.listview_layout_item,parent,false);
        }
        Student currentItem = (Student) getItem(position);
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.student_name);
        textViewItemName.setText(currentItem.name);
        TextView textViewItemClassification = (TextView)
                convertView.findViewById(R.id.student_phone);
        textViewItemClassification.setText(currentItem.phone);
        return convertView;

    }
}
