package com.example.rentmev2;

import com.example.rentmev2.Classes.*;
import com.example.rentmev2.Adapters.*;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RentList extends AppCompatActivity {
    private RecyclerView recyclerView;


    private carAdapter adapter;
    Button logoutId;
    FirebaseAuth auth;
    private DatabaseReference rentDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_list);

        FloatingActionButton fab;


        rentDB = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        logoutId = findViewById(R.id.logout);

        String userEmail = getIntent().getStringExtra("email");


        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(RentList.this,UploadActivity.class);
//                startActivity(intent);
            }
        });

        logoutId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        FirebaseRecyclerOptions<Car> options = new FirebaseRecyclerOptions.Builder<Car>()
                .setQuery(rentDB.child("Car"), Car.class).build();
        adapter = new carAdapter(options,userEmail);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    private void logout() {
        Log.d("Logout", "Logging out user");

        // Use Firebase Authentication to sign out
        FirebaseAuth.getInstance().signOut();

        Log.d("Logout", "User signed out successfully");

        // After signing out, you can redirect the user to the login page or any other page
//        Intent intent = new Intent(RentList.this, RedirectLoginSignUp.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        startActivity(intent);
//        finish();
    }

}

