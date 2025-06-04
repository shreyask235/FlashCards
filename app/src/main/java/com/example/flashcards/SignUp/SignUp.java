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

public class SignUp extends AppCompatActivity {

    TextView name, email, password, linkText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.signBtn);
        linkText = findViewById(R.id.linkText);

        btn.setOnClickListener(v -> {
            if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                saveUser(name.getText().toString(),email.getText().toString(), password.getText().toString());
                Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show();
                HomePage();
            }
        });

        linkText.setOnClickListener(v -> {
            startActivity(new Intent(SignUp.this, LogIn.class));
        });
    }

    private void HomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Name", name.getText().toString());
        startActivity(intent);
    }

    private void saveUser(String name, String email, String password) {
        SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }
}