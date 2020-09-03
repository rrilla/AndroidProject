package com.example.familyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JoinActivity extends AppCompatActivity {

    EditText edId, edPw;
    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        String sendmsg = "join.do";
        String id = edId.getText().toString();
        String pw = edPw.getText().toString();
        String result = id+pw; //자신이 보내고싶은 값을 보내시면됩니다
        new Task(sendmsg).execute(id,pw);
        /*edId = findViewById(R.id.editId);
        edPw = findViewById(R.id.editPw);
        tv = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendmsg = "join.do";
                String id = edId.getText().toString();
                String pw = edPw.getText().toString();
                String result = id+pw; //자신이 보내고싶은 값을 보내시면됩니다
                try{
                    String rst = new Task(sendmsg).execute(result).get();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });*/
    }
}