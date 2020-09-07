package com.example.familyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class AnswerPostActivity extends AppCompatActivity {

    String userId, userFcode, familyQno, question;
    String sendmsg, result;
    TextView tvQuestion;
    EditText etAnswer;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_post);

        tvQuestion = findViewById(R.id.answerpost_tv_question);
        etAnswer = findViewById(R.id.answerpost_et_answer);
        btnSubmit = findViewById(R.id.answerpost_btn_submit);

        //인텐트로 값 받기
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        userFcode = intent.getStringExtra("userFcode");
        familyQno = intent.getStringExtra("familyQno");
        question = intent.getStringExtra("question");

        tvQuestion.setText(question);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AnswerPostActivity.this);
                builder.setTitle("알림");
                builder.setMessage("작성 완료시 수정 불가능 합니다. 답변을 등록 할까요?");
                builder.setNegativeButton("아니오", null);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String answer = etAnswer.getText().toString();
                        sendmsg = null;
                        result = null;
                        try {
                            sendmsg = "answer_post.do";
                            result = new Task(sendmsg).execute(userId,userFcode,familyQno,question,answer).get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //db에 데이터 드가면 1, 에러시 0
                        if(result.equals("1")){
                            Toast.makeText(AnswerPostActivity.this, "등록 성공.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                            intent.putExtra("userId", userId);
                            startActivity(intent);
                        }else{
                            Toast.makeText(AnswerPostActivity.this, "서버 에러.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.create().show();
            }

        });
    }
}