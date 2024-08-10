package com.example.qradmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView employeeNameTextView, employeeNumberTextView, employeeIdTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        employeeNameTextView = findViewById(R.id.employeeNameTextView);
        employeeNumberTextView = findViewById(R.id.employeeNumberTextView);
        employeeIdTextView = findViewById(R.id.employeeIdTextView);

        // Retrieve the data from the intent
        Intent intent = getIntent();
        String employeeName = intent.getStringExtra("employeeName");
        String employeeNumber = intent.getStringExtra("employeeNumber");
        String employeeId = intent.getStringExtra("employeeId");

        // Display the data
        employeeNameTextView.setText("Name: " + employeeName);
        employeeNumberTextView.setText("Number: " + employeeNumber);
        employeeIdTextView.setText("ID: " + employeeId);
    }
}
