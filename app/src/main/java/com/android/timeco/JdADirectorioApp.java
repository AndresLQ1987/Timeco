package com.android.timeco;

import android.app.Application;

import com.android.timeco.Data.Repository;

public class JdADirectorioApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Repository.get(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();


    }
}
