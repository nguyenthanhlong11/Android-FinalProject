package com.lmhvteam.lmhv_foodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_intro);
    }

    public void nextIntroScreen(View view) {
        Intent intent = new Intent(this,Intro2.class);
        startActivity(intent);
    }

    public void loginScreen(View view) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}