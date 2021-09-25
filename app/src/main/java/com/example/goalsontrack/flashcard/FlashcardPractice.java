package com.example.goalsontrack.flashcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goalsontrack.R;
import com.example.goalsontrack.flashcard.model.Word;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FlashcardPractice extends AppCompatActivity {

    private static final String TAG = "Add word dialog: ";

    //variables
    ArrayList<Word> allWords = new ArrayList<>();

    //database reference
    DatabaseReference dbReference;

    //views
    Button btnShowAnswer, btnNext, btnPrevious, btnClose;
    TextView frontQuestionSide, backAnswerSide;

    //firebaseUI recyclerView
    FirebaseRecyclerOptions<Word> options;
    FirebaseRecyclerAdapter<Word, PracticeViewHolder> adapter;
    RecyclerView practiceRecyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_practice);


//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_flashcard_homepage_list_view, container, false);

        // initializing all the categories list to inside recycler view using firebaseUI
        practiceRecyclerView = findViewById(R.id.f_practice_layout_recyclerView);
        //practiceRecyclerView.setHasFixedSize(true);
        practiceRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //recycler view adapter and snapview(that is swipe pages to recycler view is add at bottom


        // getting intent's key from FlashcardListViewFragment
        String parentKey = getIntent().getStringExtra("key");
        String childKey = getIntent().getStringExtra("childKey");

        Log.i("messaage:", "message from previous activity in test is parent is :"+parentKey);
        Log.i("messaage:", "message from previous activity in test is of child is : :"+childKey);


        //setting up view variables
        btnClose = findViewById(R.id.f_btn_close_practice);
        btnShowAnswer = findViewById(R.id.f_show_answer);
        frontQuestionSide = findViewById(R.id.f_card_front);
        backAnswerSide = findViewById(R.id.f_card_back);


        //db reference
        dbReference = FirebaseDatabase.getInstance().getReference().child("Category").child(parentKey);


        // building the recyclerview
        options = new FirebaseRecyclerOptions.Builder<Word>().setQuery(dbReference, Word.class).build();
        adapter = new FirebaseRecyclerAdapter<Word, PracticeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull PracticeViewHolder holder, int position, @NonNull @NotNull Word model) {

                // this value is going to PracticeViewHolder
                holder.frontQuestionSide.setText(""+model.getWord());
                holder.backAnswerSide.setText(""+model.getDefinations());
                holder.btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish(); // close and return to calling activity
                    }
                });
                holder.btnShowAnswer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "hehehee"+position, Toast.LENGTH_SHORT).show();
                    }
                });
                Log.i("message","value of model.getWord"+model.getWord());


            }

            @NonNull
            @NotNull
            @Override
            public PracticeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flashcard_practice_format_layout, parent, false);
                return new PracticeViewHolder(view);
            }
        };
        //finally we will start the adapter
        adapter.startListening();
        practiceRecyclerView.setAdapter(adapter);

        //attaching recycler view to page so that swiping is enabled
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(practiceRecyclerView);



    }
}