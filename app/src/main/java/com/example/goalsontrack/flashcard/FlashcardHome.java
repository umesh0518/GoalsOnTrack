package com.example.goalsontrack.flashcard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goalsontrack.R;

public class FlashcardHome extends AppCompatActivity implements FlashcardAddCategoryDialog.UserInputListner {

    private static final String TAG = "Flashcard homepage:";

    private Button btnCancel;
    private ImageView plusImageButton;
    public TextView inputText;

    // variables
    String userInputText;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_home);




    }

    @Override
    protected void onResume() {
        super.onResume();

        // setting value of variables
        plusImageButton = this.findViewById(R.id.f_add_category_plus);
        inputText = findViewById(R.id.f_category_user_input);
        // views
        Button btnAdd = findViewById(R.id.f_add_category_button);
        btnCancel = findViewById(R.id.f_cancel_category_button);


        plusImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Dialog to add category opened:");
                FlashcardAddCategoryDialog dialog = new FlashcardAddCategoryDialog();
                dialog.show(getSupportFragmentManager(), "FlashcardAddCategoryDialog");


            }
        });
    }

    @Override
    public void sendUserInput(String input) {
        Log.i(TAG, "input sent from dialog was :"+ input);

    }
}