package com.lmhvteam.lmhv_foodordering;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Home extends AppCompatActivity {
    TextView textView5;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    private static final String TAG = "TAG";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        textView5 = findViewById(R.id.category1);

        fAuth = FirebaseAuth.getInstance();

        userId = fAuth.getCurrentUser().getUid();


//        db.collection("foods").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                String data = "";
//                    for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
//                        Food food =  documentSnapshot.toObject(Food.class);
//                        food.setDocumentId(documentSnapshot.getId());
//
//                        String title = food.getTitle();
//                        String id = food.getDocumentId();
//                        String image = food.getImage();
//                        String price = food.getPrice();
//
//                        data += title + "\n";
//
//                    }
//                textView5.setText(data);
//            }
//        });
//        db.collection("foods").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                String data = "";
//                if(task.isSuccessful()){
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.d(TAG, document.getId() + " => " + document.getData());
//                        Food food =  document.toObject(Food.class);
//                        food.setDocumentId(document.getId());
//
//                        String title = food.getTitle();
//                        String id = food.getDocumentId();
//                        String image = food.getImage();
//                        String price = food.getPrice();
//
//                        data += title + "\n";
//                    }
//                    textView5.setText(data);
//                } else {
//                    Log.w(TAG, "Error getting documents.", task.getException());
//                }
//            }
//        });
    }

    public void detailScreen(View view){
        Intent intent = new Intent(this, detail_foods.class);
        startActivity(intent);
    }

    public void notifyScreen(View view) {
        Intent intent = new Intent(this, Notification.class);
        startActivity(intent);
    }
}