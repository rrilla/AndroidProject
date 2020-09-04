package com.example.familyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    TextView tvQuestionP, tvQuestionC;

    String userId;
    String question, q_no, f_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        tvQuestionP = findViewById(R.id.tvQuestionP);
        tvQuestionC = findViewById(R.id.tvQuestionC);



        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");




    }
}