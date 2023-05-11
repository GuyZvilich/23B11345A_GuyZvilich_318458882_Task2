package com.example.a23b11345a_guyzvilich_318458882;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class HomeActivity extends AppCompatActivity {

    private MaterialButton home_BTN_play;
    private MaterialButton home_BTN_easy;
    private MaterialButton home_BTN_hard;
    private MaterialButton home_BTN_buttons;
    private MaterialButton home_BTN_sensors;
    private MaterialButton home_BTN_records;

    boolean difficultyLevel;
    boolean sensors;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
        setClickListeners();
    }


    private void setClickListeners()
    {
        home_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(difficultyLevel,sensors);
            }
        });
        home_BTN_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficultyLevel = false;
            }
        });
        home_BTN_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficultyLevel = true;
            }
        });
        home_BTN_buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensors = false;
            }
        });
        home_BTN_sensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensors = true;
            }
        });
        home_BTN_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecord();
            }
        });
    }


    private void findViews() {
        home_BTN_play = findViewById(R.id.home_BTN_play);
        home_BTN_easy = findViewById(R.id.home_BTN_easy);
        home_BTN_hard = findViewById(R.id.home_BTN_hard);
        home_BTN_buttons = findViewById(R.id.home_BTN_buttons);
        home_BTN_sensors = findViewById(R.id.home_BTN_sensors);
        home_BTN_records = findViewById(R.id.home_BTN_records);
    }

    private void startGame(boolean difficultyLevel, boolean sensors)
    {
        Intent playIntent = new Intent(this,MainActivity.class);
        playIntent.putExtra(MainActivity.KEY_DIFFICULTY,difficultyLevel);
        playIntent.putExtra(MainActivity.KEY_SENSORS,sensors);
        startActivity(playIntent);
        finish();
    }

    private void showRecord() {
        Intent recordIntent = new Intent(this,RecordActivity.class);
        recordIntent.putExtra(RecordActivity.KEY_AFTER_GAME,false);
        startActivity(recordIntent);
        finish();
    }

}