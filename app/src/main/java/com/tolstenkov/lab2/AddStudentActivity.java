package com.tolstenkov.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputEditText;

public class AddStudentActivity extends AppCompatActivity {

    private int index = 0;
    private Student student;
    private TextInputEditText student_title_name;
    private TextInputEditText student_title_class;
    private TextInputEditText student_size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Intent intent = this.getIntent();
        index = intent.getIntExtra("index",-1);

        student_title_name = findViewById(R.id.student_title_name);
        student_title_class = findViewById(R.id.student_title_email);
        student_size = findViewById(R.id.student_title_phone);

        if (Boolean.getBoolean(String.valueOf(index)) || index != -1) {
            student = intent.getParcelableExtra("student");
            student_title_name.setText(student.name);
            student_title_class.setText(student.email);
            student_title_class.setText(student.phone);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_student, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            super.onBackPressed();
            return true;
        }
        if (item.getItemId() == R.id.action_save) {
            student = new Student();
            student.name = student_title_name.getText().toString();
            student.email = student_title_class.getText().toString();
            student.phone = student_size.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("index", index);
            intent.putExtra("student", student);
            setResult(Activity.RESULT_OK,intent);
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}