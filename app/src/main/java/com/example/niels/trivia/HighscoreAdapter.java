package com.example.niels.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HighscoreAdapter extends ArrayAdapter<Highscore> {
    public HighscoreAdapter(@NonNull Context context, int resource, @NonNull List<Highscore> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Inflate categorylistitem.xml when converview is null
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categorylistitem, parent, false);
        }

        // Fills the categorylistitem.xml and returns it
        TextView textView = convertView.findViewById(R.id.categoryView);
        textView.setText(categories.get(position));
        return convertView;
}
