package com.example.goalsontrack.flashcard;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goalsontrack.R;

import org.jetbrains.annotations.NotNull;


public class PracticeViewHolder extends RecyclerView.ViewHolder {

    //setting up view variables
    Button btnNext, btnPrevious, btnClose, btnShowAnswer;
    TextView frontQuestionSide, backAnswerSide;


    public PracticeViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        //setting up view variables
        btnClose = itemView.findViewById(R.id.f_btn_close_practice);
        btnShowAnswer = itemView.findViewById(R.id.f_show_answer);
        frontQuestionSide = itemView.findViewById(R.id.f_card_front);
        backAnswerSide = itemView.findViewById(R.id.f_card_back);

        //backAnswerSide.setVisibility(INVISIBLE);

    }


}