package com.example.rentmev2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    Button logInButt;
    private FirebaseAuth mAuth;
    private EditText emailTxt, passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.txtInEmail);
        passwordTxt = findViewById(R.id.txtInPassword);
        logInButt = findViewById(R.id.btnSignIn);

        logInButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                logIn(email, password);
            }
        });
    }

    public void logIn(String email, String password){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(SignIn.this, "Logged In", Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                String errorCode = ((FirebaseException) task.getException()).toString();

                                if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                    emailTxt.setError("Invalid Credentials");
                                    passwordTxt.setError("Invalid Credentials");
                                }
                                Toast.makeText(SignIn.this, errorCode,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    public void redirect(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}