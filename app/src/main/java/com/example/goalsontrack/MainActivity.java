package com.example.goalsontrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.goalsontrack.flashcard.FlashcardHome;
import com.example.goalsontrack.moneytracking.MoneytrackingHome;
import com.example.goalsontrack.reminder.ReminderHome;
import com.example.goalsontrack.todo.TodoHome;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // to start Flashcard subsystem
    public void loadFlashcard(View view){
        Intent startFlashcard = new Intent(this, FlashcardHome.class);
        Toast.makeText(this,"Opening Flashcard", Toast.LENGTH_SHORT).show();
        startActivity(startFlashcard);

    }


    public void loadTodoList(View view){
        Intent startTodoList = new Intent(this, TodoHome.class);
        Toast.makeText(this,"Opening Todo List", Toast.LENGTH_SHORT).show();
        startActivity(startTodoList);

    }

    public void loadMoneyTracking(View view){
        Intent startMoneyTracking = new Intent(this, MoneytrackingHome.class);
        Toast.makeText(this,"Opening Money Tracking", Toast.LENGTH_SHORT).show();
        startActivity(startMoneyTracking);

    }

    public void loadReminder(View view){
        Intent startReminder = new Intent(this, ReminderHome.class);
        Toast.makeText(this,"Opening Reminders", Toast.LENGTH_SHORT).show();
        startActivity(startReminder);

    }

}