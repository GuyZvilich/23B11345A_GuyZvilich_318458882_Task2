package com.example.a23b11345a_guyzvilich_318458882;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a23b11345a_guyzvilich_318458882.Fragments.MapFragment;
import com.example.a23b11345a_guyzvilich_318458882.Fragments.RecordFragment;
import com.example.a23b11345a_guyzvilich_318458882.Interfaces.CallBack_sendCoordinates;
import com.example.a23b11345a_guyzvilich_318458882.Utilities.LocationMapSaver;
import com.google.android.material.button.MaterialButton;

public class RecordActivity extends AppCompatActivity {

    private MapFragment mapFragment;
    private RecordFragment recordFragment;
    private MaterialButton record_BTN_return;
    private LocationMapSaver locationMapSaver;
    public static final String KEY_DIFFICULTY = "KEY_DIFFICULTY";
    public static final String KEY_SCORE = "KEY_SCORE";
    public static final String KEY_AFTER_GAME = "KEY_AFTER_GAME";
    private boolean difficultyLevel;
    private String difficulty;
    private int score;
    private boolean afterGame;
    CallBack_sendCoordinates callBack_sendCoordinates = new CallBack_sendCoordinates() {
        @Override
        public void userCoordinatesChosen(double x, double y) {
            showOnMap(x,y);
        }

        private void showOnMap(double x, double y) {
            mapFragment.showOnMap(x,y);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Intent prevIntent = getIntent();
        afterGame = prevIntent.getBooleanExtra(KEY_AFTER_GAME,false);
        difficultyLevel = prevIntent.getBooleanExtra(KEY_DIFFICULTY,false);
        if(!difficultyLevel)
            difficulty = "Easy";
        else
            difficulty = "Hard";
        score = prevIntent.getIntExtra(KEY_SCORE,0);
        locationMapSaver = new LocationMapSaver(this);
        locationMapSaver.updateLocation(this);
        findViews();
        initFragments();
        beginTransactions();
        setClickListeners();
    }

    private void initFragments() {
        recordFragment = new RecordFragment(locationMapSaver.getXcoordinate(),locationMapSaver.getYcoordinate(),difficulty,score,afterGame);
        recordFragment.setCallback(callBack_sendCoordinates);
        mapFragment = new MapFragment();
    }

    private void beginTransactions() {

        getSupportFragmentManager().beginTransaction().add(R.id.record_FRAME_list,recordFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.record_FRAME_map,mapFragment).commit();
    }

    private void findViews() {
        record_BTN_return = findViewById(R.id.record_BTN_return);
    }

    private void setClickListeners()
    {
        record_BTN_return.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(RecordActivity.this,HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }

}