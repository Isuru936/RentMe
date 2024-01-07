package com.example.rentmev2;

import static android.content.ContentValues.TAG;

import com.example.rentmev2.Classes.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    TextView redirect;
    EditText txtemail, txtpassword;
    Button signUp, renterRentee;

    boolean isRenter = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        txtemail = findViewById(R.id.txtUpEmail);
        txtpassword = findViewById(R.id.txtUpPassword);
        signUp = findViewById(R.id.btnSignUp);
        renterRentee = findViewById(R.id.btnTogRenterRentee);

        redirect = findViewById(R.id.loginredirectlbl);

        renterRentee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRenter = !isRenter;
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtemail.getText().toString();
                String password = txtpassword.getText().toString();

                User user = new User(email, isRenter);
                signUp(email, password, user);
            }
        });



    }

    public void signUp(String email, String password, User user) {
        if (!TextUtils.isEmpty(email)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                saveData(user.getEmail(), user.isRenter()); // add Extra details to the database
                                Toast.makeText(SignUp.this, "Acccount Created", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {
                                // If sign in fails, display a message to the user.
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                Toast.makeText(SignUp.this, errorCode, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            txtpassword.setError("Check Password");
            txtemail.setError("Check Email");
            Toast.makeText(SignUp.this, "Empty email or passowrd", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveData(String email, boolean isRenter) {
        User user = new User(email, isRenter);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabase.child("users").child(userId).setValue(user);
    }


    public void redirect(View view) {
        Intent intent = new Intent(SignUp.this, SignIn.class);
        startActivity(intent);

    }
}