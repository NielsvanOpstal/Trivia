package com.example.niels.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import java.util.ArrayList;


import static com.example.niels.trivia.QuestionRequest.nQuestions;

public class GameActivity extends AppCompatActivity implements QuestionRequest.Callback {


    TextView questionView;
    EditText answerView;
    int questionNumber;
    ArrayList<QuestionItem> questions;
    private int score;
    private int triesLeft = 3;
    DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionView = findViewById(R.id.GameQuestion);
        answerView = findViewById(R.id.GameAnswer);

        QuestionRequest request = new QuestionRequest(this);
        request.getCategories(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


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
    public void nextQuestion() {
        Log.d("questionNumber", "" + questionNumber);
        questionNumber += 1;
        if (questionNumber < nQuestions) {
            questionView.setText(questions.get(questionNumber).getQuestion());
            answerView.setText("");
            triesLeft = 3;
        }
        else{
            Intent intent = new Intent(GameActivity.this, NameActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);

        }

    }

    public void answerSubmitted(View view) {


        String answer = questions.get(questionNumber).getAnswer();

        if (answer.equals(answerView.getText().toString())){
            score += questions.get(questionNumber).getValue();
            nextQuestion();
        }
        else if (triesLeft != 0) {
                triesLeft -= 1;
                Toast.makeText(this, "tries left: " + triesLeft, Toast.LENGTH_SHORT).show();
            }
        else{
            nextQuestion();
        }
    }

    public void SkipQuestion(View view) {
        nextQuestion();
    }
}


