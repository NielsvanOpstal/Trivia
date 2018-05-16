package com.example.niels.trivia;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.example.niels.trivia.MainActivity.myRef;

public class HighscoreRequest {

    private Context context;
    private Callback activity;

    public interface Callback {
        void gotHighscores(ArrayList<Highscore> highscores);
        void gotHighscoresError(String message);
    }

    public HighscoreRequest(Context aContext) {
        context = aContext;
    }

    public void getHighscores(Callback aActivity) {

        activity = aActivity;

        final ArrayList<Highscore> highscores = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TAGGAANWE", dataSnapshot.toString());
//                Log.d("SDOFJASDFJASLDKFALSKDJFAKSD", dataSnapshot.child("highscores").toString());
                for (DataSnapshot snapshot :dataSnapshot.getChildren()){

//                    highscores.add((Highscore) snapshot.child(snapshot.getKey()).getValue());
                    Log.d("TAGTAGTAG",snapshot.getKey());
                    Log.d("TAGTAGTAG",snapshot.toString());
                }
                activity.gotHighscores(highscores);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        });
    }

}
