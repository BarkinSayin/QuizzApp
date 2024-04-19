package com.barkinsayin.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class SolveQuestionActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewOptions;
    private RadioGroup radioGroupOptions;
    private Button buttonNext;

    private DatabaseHelper db;
    private List<Question> questions;
    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_solve_questions);

        textViewQuestion = findViewById(R.id.textViewQuestions);
        textViewOptions = findViewById(R.id.textViewOptions);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        buttonNext = findViewById(R.id.buttonNext);

        db = new DatabaseHelper(this);

        // Veritabanından kaydedilen soruları çek
        questions = db.getAllQuestions();

        if (questions.isEmpty()) {
            Toast.makeText(this, "Hiçbir soru bulunamadı", Toast.LENGTH_SHORT).show();
            // İşlem yapılabilir, örneğin geri dönebilirsiniz
            return;
        }

        // Başlangıçta ilk soruyu göster
        currentQuestionIndex = 0;
        showQuestion();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = radioGroupOptions.getCheckedRadioButtonId();
                if (selectedRadioButtonId == -1) {
                    Toast.makeText(SolveQuestionActivity.this, "Lütfen bir seçenek seçin", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    String selectedOption = selectedRadioButton.getText().toString();
                    // Seçilen cevabı işle
                    processAnswer(selectedOption);
                    // Bir sonraki soruyu göster
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.size()) {
                        showQuestion();
                    } else {
                        // Tüm soruları çözdük, başka bir eylem yapabilirsiniz
                        Toast.makeText(SolveQuestionActivity.this, "Tüm soruları çözdünüz", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void showQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        textViewQuestion.setText(currentQuestion.getQuestion());
        textViewOptions.setText("Seçenekler:\n1. " + currentQuestion.getOption1()
                + "\n2. " + currentQuestion.getOption2()
                + "\n3. " + currentQuestion.getOption3()
                + "\n4. " + currentQuestion.getOption4());
        // Önceki seçimi temizle
        radioGroupOptions.clearCheck();
    }

    private void processAnswer(String selectedOption) {
        // Burada seçilen cevabı işleyebilirsiniz, örneğin doğru/yanlış kontrolü yapabilirsiniz
        // Bu örnekte sadece seçilen cevabı gösteriyoruz
        Toast.makeText(this, "Seçilen cevap: " + selectedOption, Toast.LENGTH_SHORT).show();
    }
}