package com.example.flashcards.Cards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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
            Home();
        });
    }

    private void Home() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Title", title.getText().toString());
        intent.putExtra("Description", description.getText().toString());
        startActivity(intent);
    }
}