package com.lmhvteam.lmhv_foodordering;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Cart extends AppCompatActivity {
    private static final String TAG = "TAG" ;
    TextView username;
    TextView phone;
    TextView priceFood;
    TextView fTitle;
    ImageView fImage;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ImageButton add, minus;
    TextView orderTotal;
    TextView totalTitle;
    TextView num;
    String userId;
    Button btnOrder;
    TextView noneOrder;
    Button homeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart);

        fTitle = findViewById(R.id.foodTitle);
        fImage = (ImageView) findViewById(R.id.imageFood);
        priceFood = findViewById(R.id.foodPrice);
        add = findViewById(R.id.add);
        num = findViewById(R.id.num);
        minus = findViewById(R.id.minus);
        orderTotal = findViewById(R.id.orderTotal);
        totalTitle = findViewById(R.id.totalTitle);
        btnOrder = findViewById(R.id.btnOrder);
        noneOrder = findViewById(R.id.noneOrder);
        homeScreen = findViewById(R.id.homeScreen);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        FirebaseFirestore.getInstance().collection("carts")
                .whereEqualTo("idUser", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String title = "";
                            String price = "";
                            String image = "";
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               Carts carts =  document.toObject(Carts.class);
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                carts.setDocumentId(document.getId());

                                String idFood = carts.getDocumentId();
                                String imageFood = carts.getImageFood();
                                String titleFood = carts.getTitleFood();
                                String fPrice = carts.getPriceFood();

                                title += titleFood;
                                price += fPrice;
                                image += imageFood;
                            }
                            if(title!=""){
                                noneOrder.setVisibility(View.INVISIBLE);
                                homeScreen.setVisibility(View.INVISIBLE);
                                fTitle.setVisibility(View.VISIBLE);
                                fImage.setVisibility(View.VISIBLE);
                                priceFood.setVisibility(View.VISIBLE);
                                add.setVisibility(View.VISIBLE);
                                minus.setVisibility(View.VISIBLE);
                                num.setVisibility(View.VISIBLE);
                                orderTotal.setVisibility(View.VISIBLE);
                                totalTitle.setVisibility(View.VISIBLE);
                                btnOrder.setVisibility(View.VISIBLE);
                                fTitle.setText(title);
                                priceFood.setText(price);
                                orderTotal.setText(price);
                            } else {
                                noneOrder.setText("You have not ordered any dishes on our application. Go to the home screen to buy now!");
                                homeScreen.setVisibility(View.VISIBLE);
                                fTitle.setVisibility(View.INVISIBLE);
                                fImage.setVisibility(View.INVISIBLE);
                                priceFood.setVisibility(View.INVISIBLE);
                                add.setVisibility(View.INVISIBLE);
                                minus.setVisibility(View.INVISIBLE);
                                num.setVisibility(View.INVISIBLE);
                                orderTotal.setVisibility(View.INVISIBLE);
                                totalTitle.setVisibility(View.INVISIBLE);
                                btnOrder.setVisibility(View.INVISIBLE);
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void backToHome(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
    }
}