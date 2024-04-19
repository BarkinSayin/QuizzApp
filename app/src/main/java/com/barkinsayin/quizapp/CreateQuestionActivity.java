package com.barkinsayin.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class CreateQuestionActivity extends AppCompatActivity {

    private EditText editTextQuestion;
    private EditText editTextOption1;
    private EditText editTextOption2;
    private EditText editTextOption3;
    private EditText editTextOption4;
    private Button buttonCreateQuestion;
    private Button buttonDeleteQuestion;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_questions);

        editTextQuestion = findViewById(R.id.editTextQuestion);
        editTextOption1 = findViewById(R.id.editTextOption1);
        editTextOption2 = findViewById(R.id.editTextOption2);
        editTextOption3 = findViewById(R.id.editTextOption3);
        editTextOption4 = findViewById(R.id.editTextOption4);
        buttonCreateQuestion = findViewById(R.id.buttonCreateQuestion);

        buttonDeleteQuestion = findViewById(R.id.buttonDeleteQuestion);

        db = new DatabaseHelper(this);

        buttonCreateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = editTextQuestion.getText().toString().trim();
                String option1 = editTextOption1.getText().toString().trim();
                String option2 = editTextOption2.getText().toString().trim();
                String option3 = editTextOption3.getText().toString().trim();
                String option4 = editTextOption4.getText().toString().trim();

                if (question.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty()) {
                    Toast.makeText(CreateQuestionActivity.this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                } else {
                    // Soruyu veritabanına kaydet
                    db.addQuestion(new Question(question, option1, option2, option3, option4));
                    Toast.makeText(CreateQuestionActivity.this, "Soru başarıyla kaydedildi", Toast.LENGTH_SHORT).show();
                    // Formu temizle
                    editTextQuestion.setText("");
                    editTextOption1.setText("");
                    editTextOption2.setText("");
                    editTextOption3.setText("");
                    editTextOption4.setText("");
                }
            }
        });
        buttonDeleteQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tüm soruları silme işlemi
                db.deleteAllQuestions();
                Toast.makeText(CreateQuestionActivity.this, "Tüm sorular silindi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}