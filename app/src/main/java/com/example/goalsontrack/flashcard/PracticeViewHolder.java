package com.example.goalsontrack.flashcard;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goalsontrack.R;

import org.jetbrains.annotations.NotNull;


public class PracticeViewHolder extends RecyclerView.ViewHolder {

    //setting up view variables
    ImageButton btnClose;
    Button btnNext, btnPrevious, btnShowAnswer, btnShowQuestion;
    TextView frontQuestionSide, backAnswerSide;


    public PracticeViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        //setting up view variables
        btnClose = itemView.findViewById(R.id.f_btn_close_practice);
        btnShowAnswer = itemView.findViewById(R.id.f_show_answer);
        btnShowQuestion = itemView.findViewById(R.id.f_show_question_again);
        frontQuestionSide = itemView.findViewById(R.id.f_card_front);
        backAnswerSide = itemView.findViewById(R.id.f_card_back);


    }


}