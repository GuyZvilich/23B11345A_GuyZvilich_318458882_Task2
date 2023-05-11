package com.example.a23b11345a_guyzvilich_318458882;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.a23b11345a_guyzvilich_318458882.Interfaces.StepCallBack;
import com.example.a23b11345a_guyzvilich_318458882.Utilities.StepDetector;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_DIFFICULTY = "KEY_DIFFICULTY";
    public static final String KEY_SENSORS = "KEY_SENSORS";
    public final int ROWS = 7;
    public final int COLS = 5;
    public final int REFRESH_DELAY_EASY = 1000;
    public final int REFRESH_DELAY_HARD = 500;
    private ExtendedFloatingActionButton main_FAB_right;
    private ExtendedFloatingActionButton main_FAB_left;
    private ShapeableImageView[][] main_IMG_poops;
    private ShapeableImageView[][] main_IMG_coins;
    private ShapeableImageView[] main_IMG_stickMan;
    private MaterialTextView main_LBL_score;
    private ShapeableImageView[] main_IMG_heart;
    private GameManager gameManager;

    int refreshLevel;
    final Handler handler = new Handler();
    long startTime=0;
    boolean randomTime;
    boolean coinTime;
    boolean difficultyLevel;
    boolean sensors;
    private StepDetector stepDetector;
    private MediaPlayer mediaPlayer;


    Runnable refresh = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, refreshLevel);
            refreshUI();
            if(coinTime)
            {
                main_IMG_coins[0][gameManager.GenerateNewPosition()].setVisibility(View.VISIBLE);
                coinTime = false;
            }
            else if (randomTime)
            {
                main_IMG_poops[0][gameManager.GenerateNewPosition()].setVisibility(View.VISIBLE);
                randomTime = false;
                coinTime = true;
            }
            else {
                randomTime = true;
            }
            //gameManager.setScore(1);
            updateScore(gameManager.getScore());
        }
    };

    private void initStepDetector() {
        stepDetector = new StepDetector(this, new StepCallBack() {
            @Override
            public void stepX() {
                main_IMG_stickMan[gameManager.getCurrPosition()].setVisibility(View.INVISIBLE);
                gameManager.setCurrPosition(stepDetector.getStepsX());
                main_IMG_stickMan[gameManager.getCurrPosition()].setVisibility(View.VISIBLE);
            }
        },refreshLevel);
    }
    private void updateScore(int score) {
        if(score<10)
            main_LBL_score.setText("00"+score);
        else if(score<100)
            main_LBL_score.setText("0"+score);
        else
            main_LBL_score.setText(""+score);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent prevIntent = getIntent();
        difficultyLevel = prevIntent.getBooleanExtra(KEY_DIFFICULTY,false);
        sensors = prevIntent.getBooleanExtra(KEY_SENSORS,false);
        findViews();
        initViews();
        gameManager = new GameManager();
        handler.post(refresh);
    }

    private void refreshUI() {
        if (gameManager.getLife() == 0) {
            endGame();
        }
        for (int i = 0; i < COLS; i++) {
            main_IMG_poops[ROWS-1][i].setVisibility(View.INVISIBLE);
            main_IMG_coins[ROWS-1][i].setVisibility(View.INVISIBLE);
        }
        for (int i = ROWS-1; i >= 0; i--) {
            for (int j = COLS-1; j >= 0; j--) {
                if (main_IMG_poops[i][j].getVisibility() == View.VISIBLE) {
                    main_IMG_poops[i][j].setVisibility(View.INVISIBLE);
                    main_IMG_poops[i + 1][j].setVisibility(View.VISIBLE);
                }
                if (main_IMG_coins[i][j].getVisibility() == View.VISIBLE) {
                    main_IMG_coins[i][j].setVisibility(View.INVISIBLE);
                    main_IMG_coins[i + 1][j].setVisibility(View.VISIBLE);
                }
            }
        }
        checkCrash();
        checkCoin();
        gameManager.setScore(1);
    }

    private void endGame() {
        Intent recordIntent = new Intent(this,RecordActivity.class);
        recordIntent.putExtra(RecordActivity.KEY_AFTER_GAME,true);
        recordIntent.putExtra(RecordActivity.KEY_DIFFICULTY,difficultyLevel);
        recordIntent.putExtra(RecordActivity.KEY_SCORE,gameManager.getScore());
        startActivity(recordIntent);
        finish();
    }

    private void checkCoin() {
        if(main_IMG_coins[ROWS-1][gameManager.getCurrPosition()].getVisibility()==View.VISIBLE)
        {
            gameManager.setScore(2);
            playSound(this.mediaPlayer,0);
        }
    }

    private void checkCrash()
    {
        if(main_IMG_poops[ROWS-1][gameManager.getCurrPosition()].getVisibility()==View.VISIBLE)
        {
            main_IMG_heart[main_IMG_heart.length - gameManager.getLife()].setVisibility(View.INVISIBLE);
            gameManager.looseLife();
            playSound(this.mediaPlayer,1);
            gameManager.setScore(-1);
        }
    }


    private void findViews() {
        main_LBL_score = findViewById(R.id.main_LBL_score);
        main_FAB_left = findViewById(R.id.main_FAB_left);
        main_FAB_right = findViewById(R.id.main_FAB_right);
        main_IMG_heart = new ShapeableImageView[] {findViewById(R.id.main_IMG_hart1),findViewById(R.id.main_IMG_hart2),findViewById(R.id.main_IMG_hart3)};
        main_IMG_stickMan = new ShapeableImageView[COLS];
        String temp;
        int resId;
        for (int i=0;i<COLS;i++)
        {
            temp = "main_IMG_stickMan" + i;
            resId = getResources().getIdentifier(temp, "id", getPackageName());
            main_IMG_stickMan[i] = (ShapeableImageView) findViewById(resId);
        }

        main_IMG_poops = new ShapeableImageView[ROWS][COLS];
        for (int i = 0; i < COLS; i++)
        {
            for (int j = 0; j < ROWS; j++) {

                temp = "main_IMG_poopR" + j + "C" + i;
                resId = getResources().getIdentifier(temp, "id", getPackageName());
                main_IMG_poops[j][i] = (ShapeableImageView) findViewById(resId);
            }
      }
        main_IMG_coins = new ShapeableImageView[ROWS][COLS];
        for (int i = 0; i < COLS; i++)
        {
            for (int j = 0; j < ROWS; j++) {

                temp = "main_IMG_coinR" + j + "C" + i;
                resId = getResources().getIdentifier(temp, "id", getPackageName());
                main_IMG_coins[j][i] = (ShapeableImageView) findViewById(resId);
            }
        }
    }

    private void initViews() {
        if(!difficultyLevel)
            refreshLevel = REFRESH_DELAY_EASY;
        else
            refreshLevel = REFRESH_DELAY_HARD;
        if(!sensors) {
            main_FAB_left.setOnClickListener(v -> goLeft());
            main_FAB_right.setOnClickListener(v -> goRight());
        }
        else {
            initStepDetector();
            main_FAB_left.setVisibility(View.INVISIBLE);
            main_FAB_right.setVisibility(View.INVISIBLE);
        }
        startTime = System.currentTimeMillis();
        randomTime = false;
        coinTime = false;
    }

    private void goRight() {
        if(gameManager.getCurrPosition()<(COLS-1))
        {
            main_IMG_stickMan[gameManager.getCurrPosition()].setVisibility(View.INVISIBLE);
            main_IMG_stickMan[gameManager.getCurrPosition()+1].setVisibility(View.VISIBLE);
            gameManager.setCurrPosition(gameManager.getCurrPosition()+1);
            checkCrash();
            checkCoin();
        }
    }

    private void goLeft(){
        if(gameManager.getCurrPosition()>0)
        {
            main_IMG_stickMan[gameManager.getCurrPosition()].setVisibility(View.INVISIBLE);
            main_IMG_stickMan[gameManager.getCurrPosition()-1].setVisibility(View.VISIBLE);
            gameManager.setCurrPosition(gameManager.getCurrPosition()-1);
            checkCrash();
            checkCoin();
        }
    }

    protected void onResume() {
        super.onResume();
        if(sensors)
            stepDetector.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        stepDetector.stop();
    }
    private void playSound(MediaPlayer mediaplayer,int sound){
            if (sound==1) {
                mediaPlayer = MediaPlayer.create(this, R.raw.poop_step_sound);
                mediaPlayer.setVolume(1.0f, 1.0f);
            }
            if (sound ==0){
                mediaPlayer = MediaPlayer.create(this, R.raw.coin_sound);
                mediaPlayer.setVolume(1.0f, 1.0f);
            }
            mediaPlayer.start();
    }
}