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
        CustomList adapter = new CustomList((Activity) view.getContext(), web, imageId);
        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(adapter);
    }
    Search a(){
        return Search.this;
    }
}
