package com.example.niels.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HighscoreActivity extends AppCompatActivity implements HighscoreRequest.Callback {


    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        HighscoreRequest request = new HighscoreRequest(this);
        request.getHighscores(this);


        listView = findViewById(R.id.HighscoresList);

    }

    @Override
    public void gotHighscores(ArrayList<Highscore> highscores) {
        Log.d("TAGGOTHIGHSCORES", "GOTHIGHSCORES");

        listView.setAdapter(new HighscoreAdapter(this, 0, highscores));

    }

    @Override
    public void gotHighscoresError(String message) {
        Log.d("TAGGOTHIGHSCORES", "GOTEROROROROROROOR");

    }
}
