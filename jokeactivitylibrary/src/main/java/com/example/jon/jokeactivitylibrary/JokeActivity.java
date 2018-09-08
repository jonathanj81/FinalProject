package com.example.jon.jokeactivitylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private TextView mJokeView;
    private String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        mJokeView = findViewById(R.id.joke_view);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            mJoke = extras.getString("joke");
        }

        mJokeView.setText(mJoke);
    }
}
