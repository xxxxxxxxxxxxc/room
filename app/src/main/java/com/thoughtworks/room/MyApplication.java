package com.thoughtworks.room;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

public class MyApplication extends Application {
    private static MyApplication app;
    public AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("application", "init application");
        app = this;
        this.appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app").build();
    }

    public static MyApplication getInstance() {
        return app;
    }
}
