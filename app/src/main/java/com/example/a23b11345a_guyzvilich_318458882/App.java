package com.example.a23b11345a_guyzvilich_318458882;

import android.app.Application;

public class App extends Application {

    public void onCreate()
    {
        super.onCreate();
        SignalGenerator.init(this);
    }

}
