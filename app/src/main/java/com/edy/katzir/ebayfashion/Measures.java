package com.edy.katzir.ebayfashion;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by sean1 on 13/11/2015.
 */
public class Measures{
    static TextView bust;
    static TextView waste;
    static TextView hips;
    static SharedPreferences.Editor editor;
    static Activity activity;
    static void onCreate(View view){
        activity =(Activity)view.getContext();
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        bust=(TextView)view.findViewById(R.id.bust);
        waste=(TextView)view.findViewById(R.id.waste);
        hips=(TextView)view.findViewById(R.id.hips);
        bust.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                editor.putString(activity.getString(R.string.bust), s.toString());
                editor.commit();
            }
        });
        waste.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                editor.putString(activity.getString(R.string.waste), s.toString());
                editor.commit();
            }
        });
        hips.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                editor.putString(activity.getString(R.string.hips), s.toString());
                editor.commit();
            }
        });
    }
}
