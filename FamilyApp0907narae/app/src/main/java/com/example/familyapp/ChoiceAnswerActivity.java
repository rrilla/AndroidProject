package com.example.familyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceAnswerActivity extends AppCompatActivity {

    Button btnCamera, btnAudio, btnPost;
    String userId, userFcode, familyQno, question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_answer);

        btnCamera = findViewById(R.id.choiceanswer_btn_camerago);
        btnAudio = findViewById(R.id.choiceanswer_btn_audiogo);
        btnPost = findViewById(R.id.choiceanswer_btn_postgo);

        //인텐트로 값 받기
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        userFcode = intent.getStringExtra("userFcode");
        familyQno = intent.getStringExtra("familyQno");
        question = intent.getStringExtra("question");

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnswerCameraActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("userFcode", userFcode);
                intent.putExtra("familyQno", familyQno);
                intent.putExtra("question", question);
                startActivity(intent);
            }
        });

        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnswerAudioActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("userFcode", userFcode);
                intent.putExtra("familyQno", familyQno);
                intent.putExtra("question", question);
                startActivity(intent);
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnswerPostActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("userFcode", userFcode);
                intent.putExtra("familyQno", familyQno);
                intent.putExtra("question", question);
                startActivity(intent);
            }
        });
    }
}