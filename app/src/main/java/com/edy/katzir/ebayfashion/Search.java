package com.edy.katzir.ebayfashion;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sean1 on 13/11/2015.
 */


public class Search {

    ListView list;
    Activity activity;
    String jsonString;
    View view;
    List<SwipeListRow> items;
    SwipeListAdapter adapter;

    void onCreate(View view1) {
        view = view1;

        String url = "http://ebay.edy.co.il/search3.php?id=11554&PerPage=10&pageNumber=1";

        getJSON(url);
    }

    public void getJSON(String url) {
        JSONParse gets = new JSONParse();
        gets.execute(url);
    }

    Search a(){
        return Search.this;
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
            Log.d("DEBUG",json.toString());

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

