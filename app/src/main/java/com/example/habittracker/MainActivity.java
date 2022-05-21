package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void profile_button_clicked(View view) {
        setContentView(R.layout.activity_profile);
    }
    public void login_button_clicked(View view) {
        System.out.println("LOGINLOGINLOGINLOGINLOGINLOGINLOGINLOGINLOGINLOGINLOGIN");
        Button login_button = findViewById(R.id.login_button);
        Button logout_button = findViewById(R.id.logout_button);
        login_button.setVisibility(view.INVISIBLE);
        logout_button.setVisibility(view.VISIBLE);
    }
    public void logout_button_clicked(View view) {
        System.out.println("LOGUTLOGUTLOGUTLOGUTLOGUTLOGUTLOGUTLOGUTLOGUTLOGUTLOGUT");
        Button login_button = findViewById(R.id.login_button);
        Button logout_button = findViewById(R.id.logout_button);
        login_button.setVisibility(view.VISIBLE);
        logout_button.setVisibility(view.INVISIBLE);
    }
    public void back_to_main_activity_button_clicked(View view) {
        setContentView(R.layout.activity_main);
    }


}