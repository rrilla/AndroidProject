package com.example.familyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText edId, edPw, edPwconfirm, edName, edPhonenum , edBday, edNickname, edRole ,edFcode;
    Button btnIdCheck, btnFcodeCheck, btnSubmit;
    TextView tvIdCheck, tvFcodeCheck;
    String sendmsg;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvIdCheck = findViewById(R.id.tvIdCheck);
        tvFcodeCheck = findViewById(R.id.tvFcodeCheck);
        edId = findViewById(R.id.idinput);
        edPw = findViewById(R.id.pwinput);
        edPwconfirm = findViewById(R.id.pwconfirm);
        edName = findViewById(R.id.name);
        edPhonenum = findViewById(R.id.phonenum);
        edBday = findViewById(R.id.birthday);
        edNickname = findViewById(R.id.nickname);
        edRole = findViewById(R.id.role);
        edFcode = findViewById(R.id.fcode);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnIdCheck = findViewById(R.id.btnIdCheck);
        btnFcodeCheck = findViewById(R.id.btnFcodeCheck);
        //중복확인 버튼 클릭시(아이디)
        btnIdCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= edId.getText().toString();
                tvIdCheck.setText(id);
                if(id.equals("")){
                    tvIdCheck.setText("아이디를 입력 해주세요.");
                }else {
                    sendmsg = null;
                    result = null;
                    try {
                        sendmsg = "join_IdCheck.do";
                        result = new Task(sendmsg).execute(id).get();
                        tvIdCheck.setText(result);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnFcodeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fCode = edFcode.getText().toString();
                tvFcodeCheck.setText(fCode);
                if(fCode.equals("")){
                    tvFcodeCheck.setText("가족코드를 입력 해주세요.");
                }else {
                    sendmsg = null;
                    result = null;
                    try {
                        sendmsg = "join_FcodeCheck.do";
                        result = new Task(sendmsg).execute(fCode).get();
                        tvFcodeCheck.setText(result);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}