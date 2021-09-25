package com.example.goalsontrack.flashcard;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.goalsontrack.R;
import com.example.goalsontrack.flashcard.model.Categories;
import com.example.goalsontrack.flashcard.model.Word;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddCategoryDialog extends DialogFragment  {

    private static final String TAG = "Add category diaglog: ";

    // views
    private Button btnAdd;
    private Button btnCancel;
    private EditText inputText;
    private String sentFromDialog;


    // category class
    Categories categories = new Categories();


    // variables
    String userInputText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_flashcard_add_category,container, false);

        // setting value of view variables
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
                // passing to class categories
                //categories.setId("1");
                //categories.setTitle(userInputText);

                try{

                    if(TextUtils.isEmpty(userInputText)){
                        Toast.makeText(getActivity(), "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                    else{


                        //dbReference.push().setValue(categories);
                        Word word = new Word();
                        word.setWord("");
                        word.setDefinations("");
                        word.setSentence((""));
                        categories.setWordObj(word);

                        //Adding data to database
                        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("Category");
                        dbReference.child(userInputText).setValue(categories);
                        //dbReference = FirebaseDatabase.getInstance().getReference();// will add to root

                        getDialog().dismiss();

                        //calling fragment of homepage list
                        repalceWithListHomePage();

                    }

                }catch(Exception e){
                    Log.d("Error:", "Error occured:"+e );
                }

            }
          });
        //return the view
        return view;

    }

    void repalceWithListHomePage(){
        Fragment fragment_list_view_home = new HomepageListViewFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.flashcard_homepage_container,fragment_list_view_home); // (to be replaced, what will replace)
        ft.commit(); // finally commit
        Log.i(TAG, "flashcardHome----list  one opened--- and test value is : 11");
    }

}
