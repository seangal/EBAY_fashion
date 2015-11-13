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
        SwipeListRow slr = new SwipeListRow(view.getContext(),adapter,"test_image","https://www.google.co.il/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(adapter);
    }
    Search a(){
        return Search.this;
    }
}
