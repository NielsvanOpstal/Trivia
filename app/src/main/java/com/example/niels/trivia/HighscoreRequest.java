package com.example.niels.trivia;

import android.content.Context;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import static com.example.niels.trivia.MainActivity.MYREF;

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

        // Anonymous function to receive the values from the database
        MYREF.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Loops through each of the children of the Datasnapshot and gets the Highscore objects back
                for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                    highscores.add(snapshot.getValue(Highscore.class));
                }

                // Gives back the highscores list
                activity.gotHighscores(highscores);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                // If something went wrong reading the values, give back error message
                activity.gotHighscoresError(error.getMessage());
            }
        });
    }

}
