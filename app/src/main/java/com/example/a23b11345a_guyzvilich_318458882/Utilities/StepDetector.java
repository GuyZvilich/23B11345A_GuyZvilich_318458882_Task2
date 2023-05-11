package com.example.a23b11345a_guyzvilich_318458882.Utilities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.a23b11345a_guyzvilich_318458882.Interfaces.StepCallBack;

public class StepDetector {

    private Sensor sensor;
    private SensorManager sensorManager;
    private StepCallBack stepCallback;
    private int stepCounterX = 2;
    private long timestamp = 0;
    private int check =0;
    private SensorEventListener sensorEventListener;

    public StepDetector(Context context, StepCallBack stepCallback,int delay) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.stepCallback = stepCallback;
        this.check = delay;
        initEventListener();

    }

    private void initEventListener() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];

                calculateStep(x);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    private void calculateStep(float x) {
        if (System.currentTimeMillis() - timestamp > 500) {
            timestamp = System.currentTimeMillis();
            if (x > 4.0) {
                if(stepCounterX>0)
                    stepCounterX--;
                if (stepCallback != null)
                    stepCallback.stepX();
            }
            if (x < -4.0) {
                if(stepCounterX<4)
                    stepCounterX++;
                if (stepCallback != null)
                    stepCallback.stepX();
            }
        }
    }

    public int getStepsX() {
        return stepCounterX;
    }


    public void start() {
        sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    public void stop() {
        sensorManager.unregisterListener(
                sensorEventListener,
                sensor
        );
    }
}
