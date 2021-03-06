package com.lmhvteam.lmhv_foodordering;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    private static final String TAG = "TAG" ;
    TextView username;
    TextView phone;
    TextView priceOrder;
    TextView fTitle;
    ImageView fImage;
    TextView emptyHistory;
    Button reOrderBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        phone = findViewById(R.id.profilePhone);
        username = findViewById(R.id.profileName);
        fTitle = findViewById(R.id.foodTitle);
        priceOrder = findViewById(R.id.priceOrder);
        emptyHistory = findViewById(R.id.emptyHistory);
        fImage = (ImageView) findViewById(R.id.imageFood);
        reOrderBtn = (Button) findViewById(R.id.reOrderBtn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                phone.setText(value.getString("phone"));
                username.setText(value.getString("username"));
            }
        });

        FirebaseFirestore.getInstance().collection("orders")
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
                                Order order =  document.toObject(Order.class);
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                order.setDocumentId(document.getId());

                                String idFood = order.getIdFood();
                                String imageFood = order.getImageFood();
                                String titleFood = order.getTitleFood();
                                String totalPrice = order.getTotalPrice();

                                title += titleFood;
                                price += totalPrice;
                                image += imageFood;
                            }
                            if(title!=""){
                                fTitle.setText(title);
                                priceOrder.setText("Total: " + price);
                                emptyHistory.setVisibility(View.INVISIBLE);
                            } else {
                                fTitle.setVisibility(View.INVISIBLE);
                                priceOrder.setVisibility(View.INVISIBLE);
                                fImage.setVisibility(View.INVISIBLE);
                                reOrderBtn.setVisibility(View.INVISIBLE);
                                emptyHistory.setVisibility(View.VISIBLE);
                                emptyHistory.setText("You haven't purchased any dishes on this app yet.");
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }


    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(Profile.this, "Logout successfully!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void backToHome(View view) {
        startActivity(new Intent(getApplicationContext(),Home.class));
        finish();
    }
}