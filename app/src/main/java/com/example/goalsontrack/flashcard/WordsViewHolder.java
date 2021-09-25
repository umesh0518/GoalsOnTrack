package com.example.goalsontrack.flashcard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goalsontrack.R;

import org.jetbrains.annotations.NotNull;

public class WordsViewHolder extends RecyclerView.ViewHolder {

    //setting up view variables
    TextView word_name;
    ImageView edit_btn, delete_btn, menu_btn;


    public WordsViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        word_name = itemView.findViewById(R.id.f_word_title);
        edit_btn = itemView.findViewById(R.id.f_word_edit_btn);
        delete_btn = itemView.findViewById(R.id.f_word_delete_btn);
        menu_btn = itemView.findViewById(R.id.f_3_dot_menu);
    }
}
