package com.example.goalsontrack.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goalsontrack.R;
import com.example.goalsontrack.flashcard.model.Word;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class AllWordInCategory extends AppCompatActivity {

    //database reference
    DatabaseReference dbReference;

    //views
    //ImageView editBtn, deleteBtn, menuBtn;
    TextView wordName;

    //firebaseUI recyclerView
    FirebaseRecyclerOptions<Word> options;
    FirebaseRecyclerAdapter<Word, WordsViewHolder> adapter;
    RecyclerView wordsRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_all_word_in_category);


        // initializing all the word list to inside recycler view using firebaseUI
        wordsRecyclerView = findViewById(R.id.f_all_word_list_recyclerView);
        //practiceRecyclerView.setHasFixedSize(true);
        wordsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // getting intent's key from FlashcardListViewFragment
        String parentKey = getIntent().getStringExtra("key");
        String childKey = getIntent().getStringExtra("childKey");


//        //setting up view variables
//        wordName = findViewById(R.id.f_word_title);
//        editBtn = findViewById(R.id.f_word_edit_btn);
//        deleteBtn = findViewById(R.id.f_word_delete_btn);
//        menuBtn = findViewById(R.id.f_word_delete_btn);

        //db reference
        dbReference = FirebaseDatabase.getInstance().getReference().child("Category").child(parentKey);

        Log.i("messaage:", "message from add card activity was  :"+parentKey);
        Log.i("messaage:", "and for child it was  :"+childKey);

        // building the recyclerview
        options = new FirebaseRecyclerOptions.Builder<Word>().setQuery(dbReference, Word.class).build();
        adapter = new FirebaseRecyclerAdapter<Word, WordsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull WordsViewHolder holder, int position, @NonNull @NotNull Word model) {

                String parentId = getRef(position).getKey();
                String childId = model.getWord();

                //value is going to WordsViewHolder
                holder.word_name.setText(""+model.getWord());
                Log.i("message", "value of parent in allwordincat:"+parentId);
                Log.i("message", "value of child in allwordincat:"+childId);
                holder.edit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("TAG", "Dialog to add category opened:");
                        Intent intentEditWord = new Intent(AllWordInCategory.this, EditWord.class );
                        intentEditWord.putExtra("parentKeyId", parentKey);
                        intentEditWord.putExtra("childKeyId", childId);
                        startActivity(intentEditWord);
                    }
                });
                holder.delete_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbReference.child(childId).removeValue() ;
                        Toast.makeText(v.getContext(), "Deleted !",Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @NonNull
            @NotNull
            @Override
            public WordsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flashcard_single_word_list_layout, parent, false);
                return new WordsViewHolder(view);
            }
        };
        //finally we will start the adapter
        adapter.startListening();
        wordsRecyclerView.setAdapter(adapter);



    }
}