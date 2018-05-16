package com.example.niels.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        Intent intent = new Intent(NameActivity.this, HighscoreActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("name", name.getText().toString());
        startActivity(intent);
    }
}
