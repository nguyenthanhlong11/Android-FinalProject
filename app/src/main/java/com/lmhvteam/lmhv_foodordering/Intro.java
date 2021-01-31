package com.lmhvteam.lmhv_foodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;

public class Intro extends AppCompatActivity {
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser().getUid()!=null){
            startActivity(new Intent(getApplicationContext(), Home.class));
        } else {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
            getSupportActionBar().hide();
            setContentView(R.layout.activity_intro);
        }
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