package com.example.goalsontrack.flashcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.goalsontrack.R;
import com.example.goalsontrack.flashcard.model.Word;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class EditWord extends AppCompatActivity {

    private static final String TAG= "EditDialog";

    //view variables
    private EditText word, wordDef, wordSentence;
    private Button confirmEdit, cancelEdit;

    //Word obj
    Word wordObj = new Word();

    //other vars
    String userInputWord, userInputDef, userInputSentence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_edit_word);

        //setting of view vars
        word = findViewById(R.id.f_edit_word_name);
        wordDef =findViewById(R.id.f_edit_word_defination);
        wordSentence =findViewById(R.id.f_edit_word_sentence);
        confirmEdit =findViewById(R.id.f_confirm_edit_word);
        cancelEdit =findViewById(R.id.f_cancel_edit);



        //geting intent values
        String parentId = getIntent().getStringExtra("parentKeyId");
        String childID = getIntent().getStringExtra("childKeyId");

        //db reference
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Category").child(parentId).child(childID);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    Word wordFromDb = snapshot.getValue(Word.class);
                    word.setText(wordFromDb.getWord().toString());
                    wordDef.setText(wordFromDb.getDefinations().toString());
                    wordSentence.setText(wordFromDb.getSentence().toString());
                }else {
                    Log.i(TAG, "snapshot value is ---does not have any child----:");
                }


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        Log.i(TAG, "parentID for edit:"+parentId);
        Log.i(TAG, "childID for edit:"+childID);

        cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();// finish this activity and fo to calling one
            }
        });

        confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Word updatedWord = new Word();
                updatedWord.setWord(word.getText().toString());
                updatedWord.setDefinations(wordDef.getText().toString());
                updatedWord.setSentence(wordSentence.getText().toString());

                //Updating db
                try{
                    DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Category").child(parentId);
                    dbReference.child(updatedWord.getWord()).setValue(updatedWord);
                    Toast.makeText(v.getContext(),"Update successfull", Toast.LENGTH_SHORT).show();
                    //closing the activity
                    finish();

                }catch (Exception e){
                    Toast.makeText(v.getContext(),"Updated Failed!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}