package com.example.goalsontrack.flashcard;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goalsontrack.R;

import org.jetbrains.annotations.NotNull;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    // initializing view variables
    TextView title;
    ImageView dotMenu;
    Button buttonAddCard, buttonPractice;
    RelativeLayout parentLayout;


    public CategoryViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.flashcard_category_title);
        buttonAddCard = itemView.findViewById(R.id.flashcard_add_card_button);
        buttonPractice = itemView.findViewById(R.id.flashcard_practice_button);
        dotMenu = itemView.findViewById(R.id.flashcard_3_dot_menu);
//        parentLayout = itemView.findViewById(R.id.flashcard_single_cat_id);
    }
}
