package com.edy.katzir.ebayfashion;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by sean1 on 13/11/2015.
 */
public class Measures{
    static void onCreate(View view){
        Spinner dropdown = (Spinner)view.findViewById(R.id.gender);
        String[] items = new String[]{"Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }
}
