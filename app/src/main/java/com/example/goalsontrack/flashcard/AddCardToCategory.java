package com.example.goalsontrack.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goalsontrack.R;
import com.example.goalsontrack.flashcard.model.Word;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCardToCategory extends AppCompatActivity {

    private static final String TAG = "Add word dialog: ";

    // views
    private EditText wordTitle;
    private EditText wordDefination;
    private EditText wordSentence;
    private Button btnAdd;
    private Button btnCancel;

    //database reference
    private DatabaseReference dbReference;
    Word wordObject = new Word();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_add_card_to_category);

        // getting intent's key from previous
        String parentKey = getIntent().getStringExtra("key");
        String childKey = getIntent().getStringExtra("childKey");

        //seting title dynamically
        TextView titleToAddWord = findViewById(R.id.f_title_when_adding_card);
        titleToAddWord.setText("Add to "+parentKey);


        Log.i("messaage:", "message from previous activity in test is parent is :"+parentKey);
        Log.i("messaage:", "message from previous activity in test is of child is : :"+childKey);

        //setting up view variables
        wordTitle = findViewById(R.id.f_add_word);
        wordDefination= findViewById(R.id.f_add_word_defination);
        wordSentence = findViewById(R.id.f_add_word_sentence);
        btnAdd = findViewById(R.id.f_add_word_detail_button);
        btnCancel = findViewById(R.id.f_cancel_word_detail_button);

        //db reference
        dbReference = FirebaseDatabase.getInstance().getReference().child("Category").child(parentKey);


        //adding onclick listeners
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Closing dialog -> word details:");
                finish();// will finish this activity and return to calling

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Capturing word details from user");

                // Creating word from view
                wordObject.setWord(wordTitle.getText().toString().trim());
                wordObject.setDefinations(wordDefination.getText().toString());
                wordObject.setSentence(wordSentence.getText().toString());

                try{
                    //send data to db
                    if(TextUtils.isEmpty(wordTitle.getText().toString())){
                        Toast.makeText(AddCardToCategory.this, "Please enter a word. Field cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(wordDefination.getText().toString())){
                        Toast.makeText(AddCardToCategory.this, "Definition of word is required", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        //Saving word data under same category to database
                        dbReference.child(wordObject.getWord()).setValue(wordObject);
                        Toast.makeText(AddCardToCategory.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                        finish();// will finish this activity and return to calling

                    }

                }catch(Exception e){
                    Log.d("Error:", "Error occurred:"+e );
                }

            }
        });

    }
}