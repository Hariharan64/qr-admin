package com.example.qradmin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {

    private EditText editTextUserName, editTextUserEmail;
    private Button buttonAddUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialize UI elements
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserEmail = findViewById(R.id.editTextUserEmail);
        buttonAddUser = findViewById(R.id.buttonAddUser);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Set onClickListener for the button
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserDetails();
            }
        });
    }

    private void addUserDetails() {
        // Get user inputs
        String userName = editTextUserName.getText().toString();
        String userEmail = editTextUserEmail.getText().toString();

        // Validate inputs
        if (userName.isEmpty() || userEmail.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a unique user ID
        String userId = databaseReference.push().getKey();

        // Create user data map
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", userName);
        userDetails.put("email", userEmail);

        // Store user details in Firebase Realtime Database
        if (userId != null) {
            databaseReference.child(userId).setValue(userDetails);
            Toast.makeText(this, "User details added successfully", Toast.LENGTH_SHORT).show();

            // Clear the input fields
            editTextUserName.setText("");
            editTextUserEmail.setText("");
        } else {
            Toast.makeText(this, "Error: User ID is null", Toast.LENGTH_SHORT).show();
        }
    }
}
