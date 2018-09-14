package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jon.jokeactivitylibrary.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class MainActivityFragment extends Fragment {

    public static String mJokeText;
    CountingIdlingResource mIdlingRes = new CountingIdlingResource("name");
    private ProgressBar mLoadingBar;

    public MainActivityFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main, container, false);

        mLoadingBar = root.findViewById(R.id.loading_indicator);

        Button jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask().execute();
            }
        });

        return root;
    }

    class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private MyApi myApiService = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mIdlingRes.increment();
            mLoadingBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://192.168.0.4:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

                myApiService = builder.build();
            }

            try {
                return myApiService.findJoke().execute().getData();
            } catch (IOException e) {
                Log.i("JOKE-CONNECT-ERROR", e.getMessage());
                return "";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mJokeText = result;
            mIdlingRes.decrement();
            mLoadingBar.setVisibility(View.GONE);
            Intent toJokeActivity = new Intent(getActivity(), JokeActivity.class);
            toJokeActivity.putExtra("joke", result);
            startActivity(toJokeActivity);
        }
    }

    public CountingIdlingResource getmIdlingRes() {
        return mIdlingRes;
    }
}
