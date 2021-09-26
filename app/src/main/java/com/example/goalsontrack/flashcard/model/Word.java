package com.example.goalsontrack.flashcard.model;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.goalsontrack.R;
import com.example.goalsontrack.flashcard.AddCardToCategory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Word {
    private String word;
    private String definations;
    private String sentence;
    private String imageName;

    public Word() {
    }

    public Word(String word, String definations, String sentence, String imageName) {
        this.word = word;
        this.definations = definations;
        this.sentence = sentence;
        this.imageName = imageName;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinations() {
        return definations;
    }

    public void setDefinations(String definations) {
        this.definations = definations;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
