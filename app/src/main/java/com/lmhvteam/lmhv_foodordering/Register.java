package com.lmhvteam.lmhv_foodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private static final String TAG = "TAG";
    EditText mUsername, mEmail, mPassword, mRepassword, mPhone;
    Button mRegisterBtn, mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        mUsername = findViewById(R.id.edtUsername);
        mEmail = findViewById(R.id.edtEmail);
        mPhone = findViewById(R.id.edtPhone);
        mPassword = findViewById(R.id.edtPassword);
        mRepassword = findViewById(R.id.edtRePassword);
        mRegisterBtn = findViewById(R.id.btnSignup);
        mLoginBtn = findViewById(R.id.btnLogin);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mRegisterBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String username = mUsername.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String pwd = mPassword.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String repwd = mRepassword.getText().toString().trim();

                if(TextUtils.isEmpty(username)){
                    mUsername.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    mPhone.setError("Phone is required");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    mPassword.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(repwd)){
                    mRepassword.setError("Username is required");
                    return;
                }

                if(pwd.length() < 8){
                    mPassword.setError("Password should be have more than 8 characters");
                    return;
                }
                if(pwd.length() > 20){
                    mPassword.setError("Password should be have less than 20 characters");
                    return;
                }

//                if(repwd != pwd){
//                    mRepassword.setError("Please re-enter the password!");
//                    return;
//                }

                fAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created!", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("username", username);
                            user.put("email", email);
                            user.put("phone", phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: id: " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: id: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        } else {
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        }
                });
        }});
    }

    public void loginScreen(View view) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}