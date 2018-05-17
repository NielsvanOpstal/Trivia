package com.example.niels.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


import static com.example.niels.trivia.QuestionRequest.NQUESTIONS;

public class GameActivity extends AppCompatActivity implements QuestionRequest.Callback {

    TextView questionView;
    EditText answerView;
    int questionNumber;
    ArrayList<QuestionItem> questions;
    private int score;
    private int triesLeft = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionView = findViewById(R.id.GameQuestion);
        answerView = findViewById(R.id.GameAnswer);

        // Makes a QuestionRequest and then requests the categories
        QuestionRequest request = new QuestionRequest(this);
        request.getCategories(this);

    }

    @Override
    public void gotCategories(ArrayList<QuestionItem> aQuestions) {

        // Here the questions are received so the first question is shown in the game activity
        questionNumber = 0;
        questions = aQuestions;
        QuestionItem question = aQuestions.get(questionNumber);
        questionView.setText(question.getQuestion());

    }

    @Override
    public void gotCategoriesError(String message) {

        // Shows toast when questions could not be received
        Toast.makeText(this, "Could not load questions", Toast.LENGTH_SHORT).show();
        Log.d("GameActivity","Error in loading questions");

    }
    public void nextQuestion() {

        // Increments the number of the current question
        questionNumber += 1;


        if (questionNumber < NQUESTIONS) {

            // Loads next question if there are more questions
            questionView.setText(questions.get(questionNumber).getQuestion());
            answerView.setText("");
            triesLeft = 3;
        }
        else{

            // If there are no more questions, sends you to the activity to enter you name for the highscore
            Intent intent = new Intent(GameActivity.this, NameActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
    }

    public void answerSubmitted(View view) {

        // Gets the answer of the current question
        String answer = questions.get(questionNumber).getAnswer();


        if (answer.equals(answerView.getText().toString())){

            // If correct answer was given, add value to the count and call nextQuestion()
            score += questions.get(questionNumber).getValue();
            nextQuestion();
        }
        else if (triesLeft != 1) {

            // If incorrect answer was given and there are tries left, decrease tries by one and show number of tries left to user
            triesLeft -= 1;
            Toast.makeText(this, "tries left: " + triesLeft, Toast.LENGTH_SHORT).show();
            }
        else{

            // Show answer in dialog and calls nextQuestion()
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(answer)
                    .setTitle("Answer");
            AlertDialog dialog = builder.create();
            dialog.show();

            nextQuestion();
        }
    }

    public void SkipQuestion(View view) {

        // Calls nextQuestion
        nextQuestion();
    }
}


