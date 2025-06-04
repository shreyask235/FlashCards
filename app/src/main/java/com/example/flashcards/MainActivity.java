package com.example.flashcards;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.flashcards.Cards.AddFlashCard;

public class MainActivity extends AppCompatActivity {

    TextView greeting, title, description;
    Button btn;
    FrameLayout frameLayout;
    View front, back;

    private boolean isBackVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Access to the elements
        title = findViewById(R.id.Title);
        description = findViewById(R.id.Description);
        frameLayout = findViewById(R.id.card);
        front = findViewById(R.id.card_front);
        back = findViewById(R.id.card_back);
        greeting = findViewById(R.id.greeting);
        btn = findViewById(R.id.addFlashcard);

        SharedPreferences pref_user = getSharedPreferences("user_data", MODE_PRIVATE);
        SharedPreferences pref_card = getSharedPreferences("FlashCard", MODE_PRIVATE);
        Intent intent = getIntent();
        greeting.setText("Hi " + pref_user.getString("name", null));
        title.setText(pref_card.getString("Title", null));
        description.setText(pref_card.getString("Description", null));

        // listeners
        btn.setOnClickListener(v -> {
            AddPage();
        });

        frameLayout.setOnClickListener(v -> {
            flipCard(front, back, isBackVisible);
            isBackVisible = !isBackVisible;
        });
    }

    private void AddPage() {
        Intent intent = new Intent(this, AddFlashCard.class);
        startActivity(intent);
    }

    private void flipCard(View front, View back, boolean isBackVisible) {
        if (isBackVisible) {
            front.setRotationY(-180f);
            front.setVisibility(View.VISIBLE);
            front.animate().rotationY(0f).setDuration(300);
            back.animate().rotationY(180f).setDuration(300).withEndAction(() -> back.setVisibility(View.GONE));
        } else {
            back.setRotationY(180f);
            back.setVisibility(View.VISIBLE);
            back.animate().rotationY(0f).setDuration(300);
            front.animate().rotationY(-180f).setDuration(300).withEndAction(() -> front.setVisibility(View.GONE));
        }
    }

    private void AddCard() {
        FrameLayout frameLayout;
        View front, back;

    }


}