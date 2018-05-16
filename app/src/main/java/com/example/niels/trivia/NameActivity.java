package com.example.niels.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.niels.trivia.MainActivity.myRef;

public class NameActivity extends AppCompatActivity {

    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        score = getIntent().getIntExtra("score", 0);
        TextView scoreField = findViewById(R.id.NameScore);
        scoreField.setText("score: " + score);
    // onbackpressed
}

    public void nameSubmitted(View view) {
        EditText name = findViewById(R.id.NameName);
        Highscore highscore = new Highscore(score, name.getText().toString());
        myRef.push().setValue(highscore);

        startActivity(new Intent(NameActivity.this, HighscoreActivity.class));
    }
}
