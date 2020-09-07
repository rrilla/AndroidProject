package com.example.familyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class QuestionActivity extends AppCompatActivity {

    TextView tvQuestionP, tvQuestionC;
    Button btnChoice, btnYester;
    Button btnLogout, btnStorage;
    String userId, sendmsg, result;
    String userFcode, userPosition, familyQno, question_p, question_c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        tvQuestionP = findViewById(R.id.question_tv_parent);
        tvQuestionC = findViewById(R.id.question_tv_child);
        btnChoice = findViewById(R.id.question_btn_choiceanswer);
        btnYester = findViewById(R.id.question_btn_answeryesterday);
        btnLogout = findViewById(R.id.btnLogout);

        //로그인페이지에서 로그인한 아이디 인텐트로 가져오기
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        //사용자 아이디로 question페이지에 필요한 정보 서버에서 json으로 받아오기
        question_load();

        btnYester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnswerYesterdayActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("fCode", userFcode);
                startActivity(intent);
            }
        });

        btnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChoiceAnswerActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("userFcode", userFcode);
                intent.putExtra("familyQno", familyQno);
                //멤버의 포지션따라 질문 다르게 가져가야함.
                if(userPosition.equals("son")||userPosition.equals("child")){
                    intent.putExtra("question", question_c);
                }else{
                    intent.putExtra("question", question_p);
                }
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), P_StorageActivity.class);
                startActivity(intent);
            }
        });






    }

    private void question_load() {
        sendmsg = null;
        result = null;
        try {
            sendmsg = "question_load.do";
            result = new Task(sendmsg).execute(userId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //서버에서 json 제대로 받아왔는지 안왔는지
        if(result.equals("서버 에러")){
            //서버에서 못 받아왔을시 나타낼 이벤트 코딩(toast,alert 등)
        }else{
            //서버에서 json 받아왔을 시
            try {
                JSONArray jArray = new JSONObject(result).getJSONArray("questionJson");
                if (jArray != null) {
                    JSONObject jsonObject = jArray.getJSONObject(0);
                    userFcode = jsonObject.getString("userFcode");
                    userPosition = jsonObject.getString("userPosition");
                    familyQno = jsonObject.getString("familyQno");
                    question_p = jsonObject.getString("question_p");
                    question_c = jsonObject.getString("question_c");
                    tvQuestionP.setText(question_p);
                    tvQuestionC.setText(question_c);
                } else {
                    //안드로이드로 json올 때 빈 값으로 왔을 시
                }
            } catch (Exception e) {}
        }
    }
}