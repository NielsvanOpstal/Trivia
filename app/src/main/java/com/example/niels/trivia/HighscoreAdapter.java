package com.example.niels.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HighscoreAdapter extends ArrayAdapter<Highscore> {

    ArrayList<Highscore> highscores;

    public HighscoreAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Highscore> objects) {
        super(context, resource, objects);

        // Puts the received Highscores in a classwide list
        highscores = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Inflate highscorelistitem.xml when convertview is null
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscorelistitem, parent, false);
        }

        // Fills the highscorelistitem.xml and returns it
        TextView name = convertView.findViewById(R.id.HighscoreName);
        TextView score = convertView.findViewById(R.id.HighscoreScore);
        name.setText(highscores.get(position).getName());
        score.setText("Score: " + highscores.get(position).getScore());

        return convertView;
    }
}

