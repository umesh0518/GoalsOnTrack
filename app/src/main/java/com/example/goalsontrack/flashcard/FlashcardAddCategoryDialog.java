package com.example.goalsontrack.flashcard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.goalsontrack.R;


public class FlashcardAddCategoryDialog extends DialogFragment  {

    private static final String TAG = "Add category diaglog: ";

    // views
    private Button btnAdd;
    private Button btnCancel;
    public EditText inputText;
    private String sentFromDialog;

    // variables
    String userInputText;


    // creating interface outside the class just for this small purpose does not make sense so nested interface
    public interface UserInputListner{
        public void sendUserInput(String input); // implement the method is done in FlashcardHome
    }
    // using inteface
    public UserInputListner userInputListner;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_flashcard_add_category,container, false);

        // setting value of variables
        inputText =view.findViewById(R.id.f_category_user_input);
        btnAdd = view.findViewById(R.id.f_add_category_button);
        btnCancel = view.findViewById(R.id.f_cancel_category_button);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Closing dialog:");
                getDialog().dismiss();


            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Capturing input from user:");
                userInputText = inputText.getText().toString();

                Log.i("input was: ", userInputText);

                // using listener interface which is a
                userInputListner.sendUserInput(userInputText);

                getDialog().dismiss();

            }
        });
        //return the view
        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            userInputListner = (UserInputListner) getActivity(); // userInputLister will store the activity when its attatched
        }catch(Exception e){
            Log.e(TAG, "onAttach error: " + e);
        }
    }
}
