package com.example.a23b11345a_guyzvilich_318458882.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class MySP {

    private static final String DB_FILE = "DB_FILE";
    private SharedPreferences sharedPreferences;
    private static MySP instance = null;

    public MySP(Context context)
    {
        sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
    }

    public static void init(Context context){
        if(instance==null)
        {
            instance = new MySP(context);
        }
    }
    public static MySP getInstance(){
        return instance;
    }

    public  String getString(String key, String value){
        return sharedPreferences.getString(key,value);
    }

    public  void putString( String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public  int getInt(String key, int value){
        return sharedPreferences.getInt(key,value);
    }

    public  void putInt( String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }
}
