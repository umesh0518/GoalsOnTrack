package com.example.goalsontrack.flashcard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.goalsontrack.R;


public class EmptyHomepageFragment extends Fragment {


    private ImageView plusImageButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flashcard_empty_homepage, container, false);


        //############ handling dialog popup ###########

        // setting value of variables
        plusImageButton = view.findViewById(R.id.f_add_category_plus);
        plusImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Dialog to add category opened:");
                AddCategoryDialog dialog = new AddCategoryDialog();
                dialog.show(getChildFragmentManager(), "AddCategoryDialog");


            }
        });

        return view;
    }
}