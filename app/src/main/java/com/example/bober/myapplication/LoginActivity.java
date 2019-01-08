package com.example.bober.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.example.bober.myapplication.db.DbService;
import com.example.bober.myapplication.db.models.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton btnLogIn;
    AppCompatButton btnLogUp;
    AppCompatEditText inputEmail;
    AppCompatEditText inputPass;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        trySignIn();
        inputEmail = findViewById(R.id.editText);
        inputPass = findViewById(R.id.editText2);
        btnLogIn = findViewById(R.id.btn1);
        btnLogIn.setOnClickListener(this);
        btnLogUp = findViewById(R.id.btn2);
        btnLogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        tryAuthorizate();
    }

    private void trySignIn(){
        DbService dbService = new DbService();
        User user = dbService.readUser();
        if (user != null){
            if (user.isActive()) {
                Intent intent = new Intent(this,DevicesActivity.class);
                startActivity(intent);
            }
        }
    }

    private void tryAuthorizate() {
        DbService dbService = new DbService();
        User user = dbService.readUser();
        if (user != null){
            if (inputEmail.getText().toString().equals(user.getEmail())
                    && inputEmail.getText().toString().contains("@gmail.com")){
                if (inputPass.getText().toString().equals(user.getPass())
                        && inputPass.getText().toString().length() > 3 ){
                    Intent intent = new Intent(this, DevicesActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(this,"Wrong,pass",Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,"Wrong,email",Toast.LENGTH_SHORT).show();
            }
        }else  {
            Toast.makeText(this,"Wrong,register",Toast.LENGTH_SHORT).show();
        }
    }
}
