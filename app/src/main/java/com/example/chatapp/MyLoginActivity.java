package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyLoginActivity extends AppCompatActivity {

    private EditText editTextEmail,editTextPassword;
    private Button buttonSignIn;
    private TextView textViewSignOut;
    private TextView textViewForget;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null)
        {
            Intent intent = new Intent(MyLoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);

        editTextEmail = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        textViewSignOut = findViewById(R.id.textViewSignUpLogin);
        textViewForget = findViewById(R.id.textViewForgotPassword);

        auth = FirebaseAuth.getInstance();

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (!email.equals("") && !password.equals(""))
                {
                    signIn(email,password);
                }
                else
                {
                    Toast.makeText(MyLoginActivity.this, "Please enter an email and password.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        textViewSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyLoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        textViewForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyLoginActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });
    }

    public void signIn(String email,String password)
    {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    Intent intent = new Intent(MyLoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(MyLoginActivity.this, "Sign in is successful.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MyLoginActivity.this, "Sign in is not successful.", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}