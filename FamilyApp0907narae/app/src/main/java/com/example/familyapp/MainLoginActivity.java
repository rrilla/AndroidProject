package com.example.familyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainLoginActivity extends AppCompatActivity {

    Button btnQuestion, btnStorage, btnFmodify;
    String userId, sendmsg, result;
    String fCode, fName, motoo, location;
    TextView tvFname, tvMotoo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        btnQuestion = findViewById(R.id.mainlogin_btn_goQuestion);
        btnStorage = findViewById(R.id.mainlogin_btn_goStorage);
        btnFmodify = findViewById(R.id.mainlogin_btn_goFmodify);
        tvFname = findViewById(R.id.mainlogin_tv_fname);
        tvMotoo = findViewById(R.id.mainlogin_tv_motoo);

        //로그인페이지에서 로그인한 아이디 인텐트로 가져오기
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        //인텐트로 받은 아이디로 로그인 메인페이지 데이터 json으로 받아오기
        mainLogin_load();

        btnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        btnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), P_StorageActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        btnFmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FmodifyActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

    }

    private void mainLogin_load() {
        sendmsg = null;
        result = null;
        try {
            sendmsg = "mainLogin_load.do";
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
                JSONArray jArray = new JSONObject(result).getJSONArray("mainJoinJson");
                if (jArray != null) {
                    JSONObject jsonObject = jArray.getJSONObject(0);
                    fCode = jsonObject.getString("fCode");
                    fName = jsonObject.getString("fName");
                    location = jsonObject.getString("location");
                    motoo = jsonObject.getString("motoo");
                    tvMotoo.setText(motoo);
                    tvFname.setText(fName);
                } else {
                    //안드로이드로 json올 때 빈 값으로 왔을 시
                }
            } catch (Exception e) {}
        }
    }
}