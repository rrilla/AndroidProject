package com.example.familyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edId, edPw;
    TextView tvJoinGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edId = findViewById(R.id.login_id);
        edPw = findViewById(R.id.login_pw);
        btnLogin = findViewById(R.id.btnLogin);
        tvJoinGo = findViewById(R.id.login_tv_joingo);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edId.getText().toString();
                String pw = edPw.getText().toString();
                if(id.equals("")){
                    Toast.makeText(LoginActivity.this, "아이디를 입력 해주세요.", Toast.LENGTH_LONG).show();
                }else if(pw.equals("")) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력 해주세요.", Toast.LENGTH_LONG).show();
                }else{
                    edId.getText().toString();
                    edPw.getText().toString();

                    try {
                        String sendmsg = "login.do";
                        String result = new Task(sendmsg).execute(id,pw).get();
                        if(result.equals("1")){
                            //로그인 성공시 코드
                            //id, f_code 담아가기
                            Intent intent = new Intent(getApplicationContext(), MainLoginActivity.class);
                            intent.putExtra("userId", id);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "로그인 성공.", Toast.LENGTH_LONG).show();
                        }else if(result.equals("0")){
                            Toast.makeText(LoginActivity.this, "비밀번호 에러.", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "아이디 에러.", Toast.LENGTH_LONG).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tvJoinGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}