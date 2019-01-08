package com.example.bober.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.example.bober.myapplication.db.DbService;
import com.example.bober.myapplication.db.models.User;

public class RegisterActivity extends AppCompatActivity {
    private AppCompatButton btnLogUp;
    private AppCompatEditText inputEmail;
    private AppCompatEditText inputPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnLogUp = findViewById(R.id.activity_register_btn1);
        inputEmail = findViewById(R.id.activity_register_editText);
        inputPass = findViewById(R.id.activity_register_editText2);
        btnLogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }
    private void register(){
        DbService dbService = new DbService();
        User user = dbService.readUser();
        if (user == null
                && inputEmail.getText().toString().length() != 0
                && inputPass.getText().toString().length() != 0
                && inputPass.getText().toString().length() > 3
                && inputEmail.getText().toString().contains("@gmail.com")){
            dbService.createUser(inputEmail.getText().toString(),inputPass.getText().toString());
            Intent intent = new Intent(this,DevicesActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Input Error", Toast.LENGTH_SHORT).show();
        }
    }
}
