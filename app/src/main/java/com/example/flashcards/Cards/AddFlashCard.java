package com.example.flashcards.Cards;

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

public class AddFlashCard extends AppCompatActivity {

    TextView title, description;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_flash_card);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        title = findViewById(R.id.FlashTitle);
        description = findViewById(R.id.FlashDescription);
        btn = findViewById(R.id.submit);

        btn.setOnClickListener(v -> {
            if(!validate()) {
                Save(title.getText().toString(), description.getText().toString());
                Home();
            }
            else {
                Toast.makeText(this, "Missing Sections", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Home() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Save(String title, String description) {
        SharedPreferences prefs = getSharedPreferences("FlashCard", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Title", title);
        editor.putString("Description", description);
        editor.apply();
    }

    private boolean validate() {
        return title.getText().toString().isEmpty() || description.getText().toString().isEmpty();
    }
}