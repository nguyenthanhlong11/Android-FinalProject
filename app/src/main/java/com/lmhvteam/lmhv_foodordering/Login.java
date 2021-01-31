package com.lmhvteam.lmhv_foodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mRegisterBtn, mLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.edtEmail);
        mPassword = findViewById(R.id.edtPassword);

        fAuth = FirebaseAuth.getInstance();

        mRegisterBtn = findViewById(R.id.btnSignup);
        mLoginBtn = findViewById(R.id.btnLogin);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String pwd = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    mPassword.setError("Username is required");
                    return;
                }

                if (pwd.length() < 8) {
                    mPassword.setError("Password should be have more than 8 characters");
                    return;
                }
                if (pwd.length() > 20) {
                    mPassword.setError("Password should be have less than 20 characters");
                    return;
                }


                fAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Profile.class));
                        } else {
                            Toast.makeText(Login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void registerScreen(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
}