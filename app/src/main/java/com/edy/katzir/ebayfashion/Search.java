package com.edy.katzir.ebayfashion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sean1 on 13/11/2015.
 */


public class Search {

     ListView list;
     String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    };
     Integer[] imageId = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
    };
     Activity activity;

    public Search() {
    }

    void onCreate(View view){
        activity =(Activity)view.getContext();


        List<SwipeListRow> items = new ArrayList<SwipeListRow>();

        SwipeListAdapter adapter = new SwipeListAdapter(view.getContext(), R.layout.list_single, items);
        items.add(new SwipeListRow(view.getContext(),adapter,"test","http://static.adzerk.net/Advertisers/4b6eb86012864ec487b1ee4967d3c410.jpg"));
        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(adapter);
    }
    Search a(){
        return Search.this;
    }
}
