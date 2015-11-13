package com.edy.katzir.ebayfashion;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sean1 on 13/11/2015.
 */
public class Measures{
    static TextView bust;
    static TextView waste;
    static TextView hips;
    static SharedPreferences.Editor editor;
    static Activity activity;
    static SQLiteDatabase data;
    static void onCreate(View view){
        activity =(Activity)view.getContext();
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        bust=(EditText)view.findViewById(R.id.bust);
        waste=(EditText)view.findViewById(R.id.waste);
        hips=(EditText)view.findViewById(R.id.hips);

        data = activity.openOrCreateDatabase("dataBaseFashion", activity.MODE_PRIVATE, null);
        data.execSQL("CREATE TABLE IF NOT EXISTS PerData(BustSize VARCHAR,WasteSize VARCHAR, HipsSize VARCHAR, Gender VARCHAR);");
        data.execSQL("INSERT INTO PerData VALUES('', '', '', 'Female')");

        Cursor result = data.rawQuery("SELECT * FROM PerData", null);
        result.moveToFirst();

        String bust2 = result.getString(0);
        String waste2 = result.getString(1);
        String hips2 = result.getString(2);

        bust.setText(bust2);
        waste.setText(waste2);
        hips.setText(hips2);

        //String gender2 = result.getString(4);
    }

    static void Save(){
        data.execSQL("UPDATE PerData SET BustSize='"+bust.getText()+"', WasteSize='"+waste.getText()+"', HipsSize='"+hips.getText()+"', Gender='Female'");
    }
}
