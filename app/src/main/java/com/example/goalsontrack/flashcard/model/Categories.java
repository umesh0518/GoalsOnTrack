package com.example.goalsontrack.flashcard.model;

public class Categories {
    private Word wordObj;

    public Categories() {
    }

    public Categories(Word wordObj) {
        this.wordObj = wordObj;
    }

    public Word getWordObj() {
        return wordObj;
    }

    public void setWordObj(Word wordObj) {
        this.wordObj = wordObj;
    }
}
