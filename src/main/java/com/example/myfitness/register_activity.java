package com.example.myfitness;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class register_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // Initialize EditTexts
        final EditText fullNameEditText = findViewById(R.id.fullNameEditText);
        final EditText phoneEditText = findViewById(R.id.phoneEditText);
        final EditText emailEditText = findViewById(R.id.emailEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);

        // Initialize Button for registration
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract the text from the EditTexts
                String fullName = fullNameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // TODO: Implement your registration logic here
                performRegistration(fullName, phone, email, password);
            }
        });

        // Initialize Buttons for Google and Facebook sign-in
        Button googleSignInButton = findViewById(R.id.googleSignInButton);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement your Google sign-in logic here
                performGoogleSignIn();
            }
        });

        Button facebookSignInButton = findViewById(R.id.facebookSignInButton);
        facebookSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement your Facebook sign-in logic here
                performFacebookSignIn();
            }
        });

        // If you have a "Login" TextView or Button, set its OnClickListener as well.
        TextView loginPrompt = findViewById(R.id.loginPrompt);
        loginPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login screen
                finish(); // Or use an explicit intent to navigate to the login activity
            }
        });
    }

    private void performRegistration(String fullName, String phone, String email, String password) {
        // Logic for performing registration
    }

    private void performGoogleSignIn() {
        // Logic for Google sign-in
    }

    private void performFacebookSignIn() {
        // Logic for Facebook sign-in
    }
}
