package com.tolstenkov.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Student> students;
    private ListView listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        students = new ArrayList<Student>(); //(savedInstanceState != null) ? savedInstanceState.getParcelableArrayList("list") : new ArrayList<Student>();

        listItem = findViewById(R.id.listStudents);
        StudentAdapter adapter = new StudentAdapter(this,students);
        listItem.setAdapter(adapter);

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                intent.putExtra("index",position);
                intent.putExtra("student", students.get(position));
                startActivityForResult(intent,0);
            }
        });

        View fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent,0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int index = data.getIntExtra("index", -1 );
            Student student =  data.getParcelableExtra("student");
            if(index != -1){
                students.set(index, student);
            }else{
                students.add(student);
                ListView listView = findViewById(R.id.listStudents);
                StudentAdapter adapter = new StudentAdapter(this, students);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
        else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {

        outState.putParcelableArrayList("list", this.students);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NotNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.students = savedInstanceState.getParcelableArrayList("list");
        listItem = findViewById(R.id.listStudents);
        StudentAdapter adapter = new StudentAdapter(this,students);
        listItem.setAdapter(adapter);
    }
}




