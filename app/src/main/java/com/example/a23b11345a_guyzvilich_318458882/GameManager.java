package com.example.a23b11345a_guyzvilich_318458882;


import android.widget.Toast;

import com.example.a23b11345a_guyzvilich_318458882.Utilities.SignalGenerator;

import java.util.Random;

public class GameManager {


    private final int RANDOM = 5;
    private int life;
    private int currPosition;
    private int score;
    private SignalGenerator signalGenerator;

    public GameManager(){

        this.life = 3;
        this.currPosition=2;
        this.score =0;
        signalGenerator = SignalGenerator.getInstance();

    }
    public int GenerateNewPosition()
    {
        int res = new Random().nextInt(RANDOM);
        return res;
    }
    public void looseLife()
    {
        life--;
        vibrate();
        toast();
    }

    public void vibrate()
    {
        signalGenerator.vibrate(500);
    }
    public void toast()
    {
        signalGenerator.toast(" Oh, shit! 💩",Toast.LENGTH_SHORT);
    }
    public void setLife(int life)
    {
        this.life = life;
    }
    public int getLife()
    {
        return this.life;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(int position) {
        currPosition=position;

    }

    public int getScore(){return score;}
    public void setScore(int addOn)
    {
        score += addOn;
    }


}
