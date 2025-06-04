package com.example.flashcards.SignUp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flashcards.MainActivity;
import com.example.flashcards.R;

public class LogIn extends AppCompatActivity {

    TextView email, password, linkText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        linkText = findViewById(R.id.linkText);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.loginBtn);

        linkText.setOnClickListener(v -> {
            SignUp();
        });
        btn.setOnClickListener(v -> {
            if (validateCredentials()) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LogIn.this, MainActivity.class));
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    private boolean validateCredentials() {
        SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
        String savedEmail = prefs.getString("email", null);
        String savedPassword = prefs.getString("password", null);

        String inputEmail = email.getText().toString();
        String inputPassword = password.getText().toString();

        return savedEmail != null && savedPassword != null &&
                savedEmail.equals(inputEmail) && savedPassword.equals(inputPassword);
    }
}