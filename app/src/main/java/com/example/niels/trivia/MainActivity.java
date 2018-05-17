package com.example.niels.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public static DatabaseReference MYREF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gets the reference of the Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        MYREF = database.getReference("highscores");
    }

    public void gameButtonClicked(View view) {

        // Starts new game
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }

    public void HighscoreClicked(View view) {

        // Goes to the highscores
        startActivity(new Intent(MainActivity.this, HighscoreActivity.class));
    }
}
