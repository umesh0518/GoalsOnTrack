package com.example.goalsontrack.flashcard;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.goalsontrack.R;




public class FlashcardHome extends AppCompatActivity {

    private static final String TAG = "Flashcard homepage:";

    //public static FragmentManager fragmentManager;
    Fragment fragment_list_view_home = new HomepageListViewFragment();
    Fragment fragment_empty_home = new EmptyHomepageFragment();

    private int test = 11;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_home);
        Log.i(TAG, "flashcardHome----On create is called--- and test value is :"+test);

        // loading conditional fragments
        if (findViewById(R.id.flashcard_homepage_container)!=null){

            if(savedInstanceState !=null){
                return; // activity use savedInstanceState to restore previous state
            }
            else if (test ==1){
                loadEmptyHomeLayout();
            }
            else{
                loadListHomeLayout();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "On resume is called--- and test value is :"+test);
    }

    void loadEmptyHomeLayout(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.flashcard_homepage_container,fragment_empty_home); // (to be replaced, what will replace)
        ft.commit(); // finally commit
        test = test + 1;
        Log.i(TAG, "flashcardHomeempty opened and test value is :"+test);
    }

    void loadListHomeLayout() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.flashcard_homepage_container,fragment_list_view_home); // (to be replaced, what will replace)
        ft.commit(); // finally commit
        Log.i(TAG, "flashcardHomeList opened and test value is :"+test);
    }

}