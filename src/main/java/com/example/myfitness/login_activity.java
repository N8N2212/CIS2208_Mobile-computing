package com.example.myfitness;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.content.Intent;

import com.example.myfitness.ui.home.HomeFragment;

public class login_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize EditTexts
        final EditText usernameEditText = findViewById(R.id.usernameEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);

        // Initialize Button for login
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract the text from the EditTexts
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Bypass login for testing
                Intent intent = new Intent(login_activity.this, main_activity.class);
                startActivity(intent);
                finish(); // Finish the login activity
            }
        });

        // Initialize Buttons for Google and Facebook sign-in
        Button googleSignInButton = findViewById(R.id.googleSignInButton);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performGoogleSignIn();
            }
        });

        Button facebookSignInButton = findViewById(R.id.facebookSignInButton);
        facebookSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performFacebookSignIn();
            }
        });


        TextView registerPrompt = findViewById(R.id.register_prompt);
        registerPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_activity.this, register_activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(login_activity.this, main_activity.class);
                intent.putExtra("navigateTo", "home");
                startActivity(intent);
            }
        });

    }

    private void performLogin(String username, String password) {
        // Logic for performing login
    }

    private void performGoogleSignIn() {
    }

    private void performFacebookSignIn() {
    }

}
