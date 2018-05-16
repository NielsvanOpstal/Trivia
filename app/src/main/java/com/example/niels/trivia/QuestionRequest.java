package com.example.niels.trivia;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class QuestionRequest implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Callback activity;
    private Context context;
    public static final int nQuestions = 5;
    public interface Callback {
        void gotCategories(ArrayList<QuestionItem> categories);
        void gotCategoriesError(String message);
        }

    public QuestionRequest(Context aContext){
        context = aContext;
    }

    public void getCategories(Callback aActivity){
        activity = aActivity;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://jservice.io/api/random?count=" + nQuestions, this, this);
        queue.add(jsonArrayRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
        Log.d("PRINTKE", error.getMessage());
        Log.d("PRINTJE", "ERROR gekregen");
    }

    @Override
    public void onResponse(JSONArray aResponse) {
        ArrayList<QuestionItem> questions = new ArrayList<>();

        try {
            for(int i = 0; i < nQuestions; i++){
                JSONObject response = aResponse.getJSONObject(i);
                QuestionItem question = new QuestionItem();
                question.setAnswer(response.getString("answer"));
                question.setQuestion(response.getString("question"));
                question.setQuestionID(response.getInt("id"));
                question.setValue(response.getInt("value"));
                questions.add(question);

            }
            activity.gotCategories(questions);

        } catch(JSONException e){
            Log.d("Er ging iets mis", e.toString());
            getCategories(activity);
        }
    }
}
