package com.example.niels.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements QuestionRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        QuestionRequest request = new QuestionRequest(this);
        request.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

    }

    @Override
    public void gotCategoriesError(String message) {

    }
}
