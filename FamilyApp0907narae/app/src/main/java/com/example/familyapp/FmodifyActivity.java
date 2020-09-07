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
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class FmodifyActivity extends AppCompatActivity {

    String userId, fName, motoo, location;
    EditText etFname, etMotoo, etLocation;
    Button btnModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmodify);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        etFname = findViewById(R.id.fmodify_et_fname);
        etMotoo = findViewById(R.id.fmodify_et_motoo);
        etLocation = findViewById(R.id.fmodify_et_location);
        btnModify = findViewById(R.id.fmodify_btn_modify);

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fName = etFname.getText().toString();
                motoo = etMotoo.getText().toString();
                location = etLocation.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(FmodifyActivity.this);
                builder.setTitle("");
                builder.setMessage("입력하신 정보로 가족정보를 수정 하시겠습니까?");
                builder.setNegativeButton("아니오", null);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sendmsg = null;
                        String result = null;
                        try {
                            sendmsg = "fModify.do";
                            result = new Task(sendmsg).execute(userId, fName, motoo, location).get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(result.equals("1")){
                            Toast.makeText(FmodifyActivity.this, "가족정보 수정 성공.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainLoginActivity.class);
                            intent.putExtra("userId", userId);
                            startActivity(intent);
                        }else{
                            Toast.makeText(FmodifyActivity.this, "가족정보 수정 실패.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.create().show();

            }
        });


    }
}