package com.lmhvteam.lmhv_foodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class detail_foods extends AppCompatActivity {
//    ImageButton add;
//    TextView num;
//    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detail_foods);

//        num = (TextView) findViewById(R.id.num);
//        add = (ImageButton) findViewById(R.id.add);
//
//        add.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                quantity ++;
//                num.setText(quantity);
//            }
//        });

    }

    public void addToCart(View view) {
    }

    public void backToHome(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
    }

    public void cartScreen(View view) {
        startActivity(new Intent(getApplicationContext(), Cart.class));
        finish();
    }
}