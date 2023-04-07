package com.example.a23b11345a_guyzvilich_318458882;


import android.widget.Toast;

import java.util.Random;

public class GameManager {


    private final int RANDOM = 3;
    private int life;
    private int currPosition;
    private SignalGenerator signalGenerator;

    public GameManager(){

        this.life = 3;
        this.currPosition=1;
        signalGenerator = SignalGenerator.getInstance();

    }
    public int GenerateNewPoop()
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
}
