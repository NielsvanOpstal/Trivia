package com.example.niels.trivia;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.example.niels.trivia.MainActivity.myRef;

public class HighscoreRequest {

    public ArrayList<Highscore> getHighscores() {
        final ArrayList<Highscore> highscores = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable value = dataSnapshot.child("highscores").getChildren();

                for (DataSnapshot snapshot: dataSnapshot.child("highscores").getChildren()) {
                    highscores.add((Highscore) snapshot.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        });
        return highscores;
    }

}
