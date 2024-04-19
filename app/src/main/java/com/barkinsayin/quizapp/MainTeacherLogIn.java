package com.barkinsayin.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainTeacherLogIn extends AppCompatActivity {
    ImageButton goMenu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher_log_in);

        goMenu=findViewById(R.id.backpage);
        goMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent pageMenu=new Intent(MainTeacherLogIn.this, MainActivity.class);
                startActivity(pageMenu);
            }
        });
        Button loginButton = findViewById(R.id.button2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainTeacherLogIn.this, CreateQuestionActivity.class);
                startActivity(intent);
            }
        });
    }
}