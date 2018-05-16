package com.example.niels.trivia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements QuestionRequest.Callback {


    TextView questionView = findViewById(R.id.GameQuestion);
    EditText anserView = findViewById(R.id.GameAnswer);
    int questionNumber;
    ArrayList<QuestionItem> questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        QuestionRequest request = new QuestionRequest(this);
        request.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<QuestionItem> aQuestions) {
        questionNumber = 0;
        questions = aQuestions;
        QuestionItem question = aQuestions.get(0);
        questionView.setText(question.getQuestion());

    }

    @Override
    public void gotCategoriesError(String message) {

    }


}
