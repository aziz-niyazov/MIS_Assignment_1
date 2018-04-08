package com.example.zizik.mis_assignment_1;
import android.app.Activity;
import android.os.AsyncTask;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetContent extends AsyncTask<String, Void, String> {

    private TextView textView;
    private Activity activity;


    public GetContent(TextView textView, Activity activity) {
        this.textView = textView;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        String serverURL = strings[0];
        BufferedReader bufferedReader;
        String inputString;

        try {
            URL url = new URL(serverURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            urlConnection.disconnect();

            return builder.toString() + "\nEND";
        } catch (Exception e) {
            final String err = "Error: " + e.getMessage();

            //show toast message on ui thread
            //ref: https://stackoverflow.com/questions/3134683/android-toast-in-a-thread
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(activity, err, Toast.LENGTH_SHORT).show();
                }
            });

            return err;
        }
    }

    @Override
    protected void onPostExecute(String content) {
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(content);
    }
}