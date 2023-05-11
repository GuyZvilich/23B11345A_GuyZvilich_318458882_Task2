package com.example.a23b11345a_guyzvilich_318458882;

import android.app.Application;

import com.example.a23b11345a_guyzvilich_318458882.Utilities.MySP;
import com.example.a23b11345a_guyzvilich_318458882.Utilities.SignalGenerator;

public class App extends Application {

    public void onCreate()
    {
        super.onCreate();
        MySP.init(this);
        SignalGenerator.init(this);
    }

}
