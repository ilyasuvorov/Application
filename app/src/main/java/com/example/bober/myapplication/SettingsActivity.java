package com.example.bober.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.bober.myapplication.db.DbService;
import com.example.bober.myapplication.db.models.User;

public class SettingsActivity extends AppCompatActivity implements OnClickListener {

    AppCompatButton btn3;
    AppCompatButton btn4;


    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DbService dbService = new DbService();
                User user = dbService.readUser();
                user.setActive(false);
                dbService.updateUser(user);
                Intent intent = new Intent(SettingsActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, DevicesActivity.class);
        startActivity(intent);
    }
}