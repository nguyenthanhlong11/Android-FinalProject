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

public class Notification extends AppCompatActivity {
    private static final String TAG = "TAG" ;
    TextView notifyContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_notification);


        notifyContent = findViewById(R.id.notifyContent);

        FirebaseFirestore.getInstance().collection("notifies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String values = "";
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Notify notify =  document.toObject(Notify.class);
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                notify.setDocumentId(document.getId());

                                String content = notify.getContent();
                                String timeUpload =  notify.getTime();

                                values += "Time: " + timeUpload
                                        + ". \n " + content;

                            }

                            if(values!=""){
                                notifyContent.setText(values);
                            } else {
                                notifyContent.setText("You don't receive any notification yet!");
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    public void profileScreen(View view) {
        startActivity(new Intent(getApplicationContext(), Profile.class));
        finish();
    }

    public void backToHome(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
    }
}