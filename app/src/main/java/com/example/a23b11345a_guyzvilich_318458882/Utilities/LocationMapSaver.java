package com.example.a23b11345a_guyzvilich_318458882.Utilities;

import static android.location.LocationManager.GPS_PROVIDER;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

public class LocationMapSaver implements LocationListener {

    private static final int PERMISSIONS_REQUEST_LOCATION = 1;
    private double yCoordinate;
    private double xCoordinate;
    private LocationManager manager;

    @Override
    public void onLocationChanged(@NonNull Location location) {
    }

    public LocationMapSaver(AppCompatActivity activity) {
        manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_LOCATION
            );
        }
        manager.requestLocationUpdates(GPS_PROVIDER,0,0,this);
    }


    public void updateLocation(AppCompatActivity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = manager.getLastKnownLocation(GPS_PROVIDER);
            if (location != null) {
                xCoordinate = location.getLongitude();
                yCoordinate = location.getLatitude();

            }
        }
        manager.removeUpdates(this);
    }

    public double getXcoordinate() {
        return yCoordinate;
    }

    public double getYcoordinate() {
        return xCoordinate;
    }


}
