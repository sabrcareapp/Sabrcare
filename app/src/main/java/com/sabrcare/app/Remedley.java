package com.sabrcare.app;

import android.app.Application;

import io.realm.Realm;

public class Remedley extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
