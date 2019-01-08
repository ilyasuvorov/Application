package com.example.bober.myapplication.app;

import android.app.Application;

import com.example.bober.myapplication.db.models.MyObjectBox;

import io.objectbox.BoxStore;

public class App extends Application {

    private static BoxStore mDbSession;

    @Override
    public void onCreate() {
        super.onCreate();

        mDbSession = MyObjectBox.builder().androidContext(this).build();

    }

    public static BoxStore getDbSession() {
        return mDbSession;
    }

}
