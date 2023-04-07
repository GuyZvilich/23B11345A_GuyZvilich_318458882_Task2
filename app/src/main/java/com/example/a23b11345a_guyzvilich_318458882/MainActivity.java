package com.example.a23b11345a_guyzvilich_318458882;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    public final int REFRESH_DELAY = 1000;
    private ExtendedFloatingActionButton main_FAB_right;
    private ExtendedFloatingActionButton main_FAB_left;
    private ShapeableImageView[][] main_IMG_poops;
    private ShapeableImageView[] main_IMG_stickMan;
    private ShapeableImageView[] main_IMG_heart;
    private GameManager gameManager;
    final Handler handler = new Handler();
    long startTime =0;

    Runnable refresh = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, REFRESH_DELAY);
            int seconds =(int)((System.currentTimeMillis() - startTime)/1000);
            if (seconds % 2 == 0)
            {
                refreshUI();
                main_IMG_poops[0][gameManager.GenerateNewPoop()].setVisibility(View.VISIBLE);
            }
            else {
                refreshUI();
            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
        gameManager = new GameManager();
        handler.post(refresh);
    }

    private void refreshUI() {
        if (gameManager.getLife() == 0) {
            gameManager.setLife(3);
            restoreLife();
        }
        for (int i = 0; i < 3; i++) {
            main_IMG_poops[6][i].setVisibility(View.INVISIBLE);
        }
        for (int i = 6; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                if (main_IMG_poops[i][j].getVisibility() == View.VISIBLE) {
                    main_IMG_poops[i][j].setVisibility(View.INVISIBLE);
                    main_IMG_poops[i + 1][j].setVisibility(View.VISIBLE);
                }
            }
        }
        checkCrash();

    }

    private void checkCrash()
    {
        if(main_IMG_poops[6][gameManager.getCurrPosition()].getVisibility()==View.VISIBLE)
        {
            main_IMG_heart[main_IMG_heart.length - gameManager.getLife()].setVisibility(View.INVISIBLE);
            gameManager.looseLife();
        }
    }

    private void restoreLife() {
        main_IMG_heart[0].setVisibility(View.VISIBLE);
        main_IMG_heart[1].setVisibility(View.VISIBLE);
        main_IMG_heart[2].setVisibility(View.VISIBLE);
    }


    private void findViews() {
        main_FAB_left = findViewById(R.id.main_FAB_left);
        main_FAB_right = findViewById(R.id.main_FAB_right);
        main_IMG_heart = new ShapeableImageView[] {findViewById(R.id.main_IMG_hart1),findViewById(R.id.main_IMG_hart2),findViewById(R.id.main_IMG_hart3)};
        main_IMG_stickMan = new ShapeableImageView[3];
        String temp;
        int resId;
        for (int i=0;i<3;i++)
        {
            temp = "main_IMG_stickMan" + i;
            resId = getResources().getIdentifier(temp, "id", getPackageName());
            main_IMG_stickMan[i] = (ShapeableImageView) findViewById(resId);
        }

        main_IMG_poops = new ShapeableImageView[7][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 7; j++) {

                temp = "main_IMG_poopR" + j + "C" + i;
                resId = getResources().getIdentifier(temp, "id", getPackageName());
                main_IMG_poops[j][i] = (ShapeableImageView) findViewById(resId);
            }
      }
    }

    private void initViews() {
        main_FAB_left.setOnClickListener(v-> goLeft());
        main_FAB_right.setOnClickListener(v->goRight());
        startTime = System.currentTimeMillis();
    }

    private void goRight() {
        if(gameManager.getCurrPosition()<2)
        {
            main_IMG_stickMan[gameManager.getCurrPosition()].setVisibility(View.INVISIBLE);
            main_IMG_stickMan[gameManager.getCurrPosition()+1].setVisibility(View.VISIBLE);
            gameManager.setCurrPosition(gameManager.getCurrPosition()+1);
            checkCrash();
        }
    }

    private void goLeft(){
        if(gameManager.getCurrPosition()>0)
        {
            main_IMG_stickMan[gameManager.getCurrPosition()].setVisibility(View.INVISIBLE);
            main_IMG_stickMan[gameManager.getCurrPosition()-1].setVisibility(View.VISIBLE);
            gameManager.setCurrPosition(gameManager.getCurrPosition()-1);
            checkCrash();


        }
    }
}