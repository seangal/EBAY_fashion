package com.edy.katzir.ebayfashion;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String url = "http://ebay.edy.co.il/search3.php?id=11554&PerPage=10&pageNumber=1";
        getJSON(url);
    }
    public void getJSON(String url) {
        JSONParse gets = new JSONParse();
        gets.execute(url);
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(args[0]);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            jsonString = json.toString();
            Log.d("DEBUG", json.toString());

            String title = "";
            String url2 = "";

            activity = (Activity) view.getContext();

            items = new ArrayList<SwipeListRow>();

            adapter = new SwipeListAdapter(view.getContext(), R.layout.list_single, items);

            try {
                JSONObject jObj = new JSONObject(jsonString);
                JSONArray res = jObj.getJSONArray("results");
                for (int i = 0; i < res.length(); i++) {
                    url2 = res.getJSONObject(i).getString("image");
                    title = res.getJSONObject(i).getString("title");
                    items.add(new SwipeListRow(view.getContext(), adapter, title, url2));
                }
                Log.d("DEBEG", res.getJSONObject(0).toString());
            }catch (Exception e){
                Log.d("DEBUG", e.getMessage());
            }

            list = (ListView) view.findViewById(R.id.list);
            list.setAdapter(adapter);
        }
    }

}
