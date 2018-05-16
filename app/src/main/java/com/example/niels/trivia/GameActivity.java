package com.example.niels.trivia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.niels.trivia.QuestionRequest.nQuestions;

public class GameActivity extends AppCompatActivity implements QuestionRequest.Callback {


    TextView questionView;
    EditText answerView;
    int questionNumber;
    ArrayList<QuestionItem> questions;
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionView = findViewById(R.id.GameQuestion);
        answerView = findViewById(R.id.GameAnswer);

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


    public void answerSubmitted(View view) {

        String answer = questions.get(questionNumber).getAnswer();
        if (answer.equals(answerView.getText().toString())){
            score += questions.get(questionNumber).getValue();
            questionNumber += 1;
            if (questionNumber < nQuestions) {
                questionView.setText(questions.get(questionNumber).getQuestion());
                answerView.setText("");
            }
        }
    }

}
