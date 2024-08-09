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

    private EditText employeeNameEditText, employeeNumberEditText, employeeIdEditText, phoneNumberEditText;
    private Button submitButton;

    // Firebase Database Reference
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("employees");

        // Initialize UI elements
        employeeNameEditText = findViewById(R.id.employeeName);
        employeeNumberEditText = findViewById(R.id.employeeNumber);
        employeeIdEditText = findViewById(R.id.employeeId);
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        submitButton = findViewById(R.id.submitButton);

        // Set click listener for submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitEmployeeData();
            }
        });
    }

    private void submitEmployeeData() {
        String employeeName = employeeNameEditText.getText().toString().trim();
        String employeeNumber = employeeNumberEditText.getText().toString().trim();
        String employeeId = employeeIdEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();

        if (employeeName.isEmpty() || employeeNumber.isEmpty() || employeeId.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate a unique key for each entry
        String id = databaseReference.push().getKey();

        // Create a new Employee object
        Employee employee = new Employee(employeeName, employeeNumber, employeeId, phoneNumber);

        // Save the employee object in the database
        if (id != null) {
            databaseReference.child(id).setValue(employee)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Employee data saved", Toast.LENGTH_SHORT).show();
                            // Clear the form for new entries
                            clearForm();
                        } else {
                            Toast.makeText(MainActivity.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void clearForm() {
        employeeNameEditText.setText("");
        employeeNumberEditText.setText("");
        employeeIdEditText.setText("");
        phoneNumberEditText.setText("");
    }
}