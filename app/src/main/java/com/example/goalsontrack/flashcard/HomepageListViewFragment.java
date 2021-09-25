package com.example.goalsontrack.flashcard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.goalsontrack.R;
import com.example.goalsontrack.flashcard.model.Categories;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class HomepageListViewFragment extends Fragment {


    //db connection
    DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("Category");


    //firebaseUI recyclerView
    FirebaseRecyclerOptions<Categories> options;
    FirebaseRecyclerAdapter<Categories, CategoryViewHolder> adapter;
    RecyclerView recyclerViewContainer;


    //floating add button
    private ImageView addCategoryBtn;
    private ImageView deletebtn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flashcard_homepage_list_view, container, false);

        // initializing all the categories list to inside recycler view using firebaseUI
        recyclerViewContainer = view.findViewById(R.id.flashcard_recycler_view_all_category_list);
        recyclerViewContainer.setHasFixedSize(true);
        recyclerViewContainer.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // setting value of variables for dialog pop up
        addCategoryBtn = view.findViewById(R.id.floating_add_button_flashcard);
        deletebtn = view.findViewById(R.id.flashcard_3_dot_menu);

        // building the recyclerview
        options = new FirebaseRecyclerOptions.Builder<Categories>().setQuery(dbReference, Categories.class).build();
        adapter = new FirebaseRecyclerAdapter<Categories, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull CategoryViewHolder holder, int position, @NonNull @NotNull Categories model) {

                //reading values from database first way:
                String wordName = model.getWordObj().getWord();
                Log.i("checking:", "word object data:"+wordName);

                //2nd way of reading from db---better one
                String key = getRef(position).getKey();
                Log.i("checking:", "word object key:"+key);


                // this value is going to CategoryViewHolder
                holder.title.setText("" + key);
                holder.buttonAddCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), AddCardToCategory.class);
                        intent.putExtra("key",key);
                        intent.putExtra("childKey", wordName);
                        startActivity(intent);
                    }
                });
                holder.buttonPractice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), FlashcardPractice.class);
                        intent.putExtra("key",key);
                        intent.putExtra("childKey", wordName);
                        startActivity(intent);
                        Toast.makeText(getContext(), "Have fun Practicing ",Toast.LENGTH_SHORT).show();
                    }
                });

                holder.dotMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dbReference.child(key).removeValue() ;
                        Toast.makeText(getContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @NonNull
            @NotNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flashcard_single_category_layout, parent, false);
                return new CategoryViewHolder(view);
            }
        };
        //finally we will start the adapter to the recyclerview
        adapter.startListening();
        recyclerViewContainer.setAdapter(adapter);



        // adding on click listener on FAB
        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Dialog to add category opened:");
                AddCategoryDialog dialogCategory = new AddCategoryDialog();
                dialogCategory.show(getChildFragmentManager(), "AddCategoryDialog");
            }
        });

      return view;
    }
}