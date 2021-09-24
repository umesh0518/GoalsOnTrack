package com.example.goalsontrack.todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.goalsontrack.Adapter.ToDoAdapter;
import com.example.goalsontrack.Model.ToDoModel;
import com.example.goalsontrack.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoHome extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton mFab;
    private FirebaseFirestore firestore;
    private ToDoAdapter adapter;
    private List<ToDoModel> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_home);

        recyclerView = findViewById(R.id.todo_recyclerView);
        mFab = findViewById(R.id.td_floatingActionButton);
        firestore = FirebaseFirestore.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager( TodoHome.this));

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoAddNewTask.newInstance().show(getSupportFragmentManager(), TodoAddNewTask.TAG);

            }
        });
        mList = new ArrayList<>();
        adapter = new ToDoAdapter(TodoHome.this, mList);


        recyclerView.setAdapter(adapter);
        showData();
    }

    private void showData() {
        firestore.collection("task").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            //to retrieve the document id
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                 for(DocumentChange documentChange : value.getDocumentChanges()) {
                     if(documentChange.getType() == DocumentChange.Type.ADDED){
                         String id = documentChange.getDocument().getId();
                         ToDoModel toDoModel = documentChange.getDocument().toObject(ToDoModel.class).withId(id);

                         mList.add(toDoModel);
                         adapter.notifyDataSetChanged();
                     }
                 }
                Collections.reverse(mList);
            }
        });

    }
}