package com.barkinsayin.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button goStudent;
    Button goTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goStudent = findViewById(R.id.student);
        goStudent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent pageStudent = new Intent(MainActivity.this, MainStudentLogIn.class);
                startActivity(pageStudent);
            }
        });
        goTeacher=findViewById(R.id.teacher);
        goTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageTeacher = new Intent(MainActivity.this, MainTeacherLogIn.class);
                startActivity(pageTeacher);
            }
        });
    }

}
