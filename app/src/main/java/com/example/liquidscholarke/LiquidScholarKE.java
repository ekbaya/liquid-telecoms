package com.example.liquidscholarke;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class LiquidScholarKE extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
