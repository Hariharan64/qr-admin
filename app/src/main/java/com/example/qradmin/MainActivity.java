package com.example.qradmin;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextDesignation, editTextUserId, editTextPhoneNumber;
    private Button buttonSaveUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextDesignation = findViewById(R.id.editTextDesignation);
        editTextUserId = findViewById(R.id.editTextUserId);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonSaveUser = findViewById(R.id.buttonSaveUser);

        buttonSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });
    }

    private void saveUser() {
        String name = editTextName.getText().toString().trim();
        String designation = editTextDesignation.getText().toString().trim();
        String userId = editTextUserId.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        if (name.isEmpty() || designation.isEmpty() || userId.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a User object
        User user = new User(name, designation, userId, phoneNumber);

        // Save the User object to Firebase Realtime Database
        databaseReference.child(userId).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "User saved successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to save user", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}