package com.example.datajson;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchDataTask extends AsyncTask<String, Void, List<Quote>> {

    private final OnDataFetchedListener listener;

    public FetchDataTask(OnDataFetchedListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<Quote> doInBackground(String... urls) {
        String jsonString = "";
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonString += line;
                }
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parseJson(jsonString);
    }

    @Override
    protected void onPostExecute(List<Quote> result) {
        super.onPostExecute(result);
        if (listener != null) {
            listener.onDataFetched(result);
        }
    }

    private List<Quote> parseJson(String jsonString) {
        List<Quote> items = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONObject(jsonString).getJSONArray("quotes");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String content = jsonObject.getString("quote");
                String author = jsonObject.getString("author");
                items.add(new Quote(content, author));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public interface OnDataFetchedListener {
        void onDataFetched(List<Quote> items);
    }
}

