package com.example.qradmin;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPhoneNumber;
    private Button buttonAddPhone;
    private ProgressBar progressBar;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonAddPhone = findViewById(R.id.buttonAddPhone);
        progressBar = findViewById(R.id.progressBar);

        databaseReference = FirebaseDatabase.getInstance().getReference("AdminPhoneNumbers");

        buttonAddPhone.setOnClickListener(v -> {
            String phoneNumber = editTextPhoneNumber.getText().toString().trim();

            if (phoneNumber.isEmpty() || phoneNumber.length() < 10) {
                editTextPhoneNumber.setError("Please enter a valid phone number");
                editTextPhoneNumber.requestFocus();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            addPhoneNumberToDatabase(phoneNumber);
        });
    }

    private void addPhoneNumberToDatabase(String phoneNumber) {
        databaseReference.child(phoneNumber).setValue(true)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Phone number added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to add phone number", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}