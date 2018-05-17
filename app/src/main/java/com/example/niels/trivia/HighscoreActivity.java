package com.example.niels.trivia;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class HighscoreActivity extends AppCompatActivity implements HighscoreRequest.Callback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // Creates HighscoreRequest and calls getHighscores()
        HighscoreRequest request = new HighscoreRequest(this);
        request.getHighscores(this);
    }

    @Override
    public void gotHighscores(ArrayList<Highscore> highscores) {

        // Finds the listView and sets an adapter for the highscores
        ListView listView = findViewById(R.id.HighscoresList);
        listView.setAdapter(new HighscoreAdapter(this, 0, highscores));

    }

    @Override
    public void gotHighscoresError(String message) {
        Toast.makeText(this, "Could not load highscores", Toast.LENGTH_SHORT).show();
        Log.d("HighscoreActivity","Error in loading highscores");
    }
}
