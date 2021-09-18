package com.example.goalsontrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.goalsontrack.flashcard.FlashcardHome;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // to start Flashcard subsystem
    public void loadFlashcard(View view){
        Intent startFlashcard = new Intent(this, FlashcardHome.class);

        startActivity(startFlashcard);

    }
}